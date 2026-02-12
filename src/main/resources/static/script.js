const API_BASE = '/api';

async function api(path, options = {}) {
  const response = await fetch(`${API_BASE}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options
  });
  if (!response.ok) {
    const err = await response.json().catch(() => ({ message: 'Request failed' }));
    throw new Error(err.message || 'Request failed');
  }
  if (response.status === 204) return null;
  return response.json();
}

function setMessage(el, message, type = 'success') {
  el.textContent = message;
  el.className = `message ${type}`;
}

async function loadDashboard() {
  const metrics = await api('/dashboard');
  const metricsContainer = document.getElementById('metrics');
  const statusContainer = document.getElementById('statusBreakdown');

  metricsContainer.innerHTML = `
    <div class="card metric"><div class="label">Total Tasks</div><div class="value">${metrics.totalTasks}</div></div>
    <div class="card metric"><div class="label">Violations</div><div class="value">${metrics.totalViolations}</div></div>
    <div class="card metric"><div class="label">Distinct Statuses</div><div class="value">${Object.keys(metrics.tasksByStatus).length}</div></div>
  `;

  statusContainer.innerHTML = Object.entries(metrics.tasksByStatus)
    .map(([status, count]) => `<div class="status-chip"><strong>${status}</strong><div>${count}</div></div>`)
    .join('');
}

async function initTasksPage() {
  await loadEmployeesDropdown();
  await loadTasks();
  document.getElementById('taskForm').addEventListener('submit', createTask);
}

async function loadEmployeesDropdown() {
  const employees = await api('/employees');
  const dropdown = document.getElementById('assignedEmployeeId');
  dropdown.innerHTML = employees.map(e => `<option value="${e.id}">${e.fullName} (${e.role})</option>`).join('');
}

async function createTask(event) {
  event.preventDefault();
  const message = document.getElementById('taskMessage');
  try {
    const payload = {
      title: document.getElementById('title').value,
      description: document.getElementById('description').value,
      priority: document.getElementById('priority').value,
      assignedEmployeeId: Number(document.getElementById('assignedEmployeeId').value),
      approvalsCount: Number(document.getElementById('approvalsCount').value || 0)
    };
    await api('/tasks', { method: 'POST', body: JSON.stringify(payload) });
    setMessage(message, 'Task created successfully.');
    event.target.reset();
    await loadTasks();
  } catch (err) {
    setMessage(message, err.message, 'error');
  }
}

async function loadTasks() {
  const tbody = document.getElementById('taskTableBody');
  const tasks = await api('/tasks');
  const statuses = ['OPEN', 'IN_PROGRESS', 'REVIEW', 'TEST', 'DONE', 'BLOCKED'];

  tbody.innerHTML = tasks.map(task => `
    <tr>
      <td>${task.id}</td>
      <td>${task.title}</td>
      <td>${task.priority}</td>
      <td>${task.status}</td>
      <td>${task.assignedEmployeeName || '-'}</td>
      <td>${task.approvalsCount}</td>
      <td>
        <select onchange="transitionTask(${task.id}, this.value)">
          ${statuses.map(status => `<option value="${status}" ${status === task.status ? 'selected' : ''}>${status}</option>`).join('')}
        </select>
      </td>
    </tr>
  `).join('');
}

async function transitionTask(taskId, targetStatus) {
  const message = document.getElementById('taskMessage');
  try {
    let blockedReason = null;
    if (targetStatus === 'BLOCKED') {
      blockedReason = prompt('Enter blocked reason:');
    }
    await api(`/tasks/${taskId}/status`, {
      method: 'PATCH',
      body: JSON.stringify({ targetStatus, blockedReason })
    });
    setMessage(message, `Task ${taskId} moved to ${targetStatus}.`);
    await loadTasks();
    await loadDashboard().catch(() => null);
  } catch (err) {
    setMessage(message, err.message, 'error');
    await loadTasks();
  }
}

async function initRulesPage() {
  await loadRules();
  document.getElementById('ruleForm').addEventListener('submit', createRule);
}

async function createRule(event) {
  event.preventDefault();
  const message = document.getElementById('ruleMessageBox');
  try {
    const payload = {
      name: document.getElementById('ruleName').value,
      type: document.getElementById('ruleType').value,
      active: document.getElementById('active').checked,
      errorMessage: document.getElementById('ruleMessage').value,
      sourceStatus: valueOrNull('sourceStatus'),
      targetStatus: valueOrNull('targetStatus'),
      priorityCondition: valueOrNull('priorityCondition'),
      minApprovals: numberOrNull('minApprovals'),
      executionOrder: Number(document.getElementById('executionOrder').value)
    };
    await api('/rules', { method: 'POST', body: JSON.stringify(payload) });
    setMessage(message, 'Rule created successfully.');
    event.target.reset();
    await loadRules();
  } catch (err) {
    setMessage(message, err.message, 'error');
  }
}

function valueOrNull(id) {
  const v = document.getElementById(id).value.trim();
  return v ? v : null;
}

function numberOrNull(id) {
  const v = document.getElementById(id).value;
  return v === '' ? null : Number(v);
}

async function loadRules() {
  const tbody = document.getElementById('rulesTableBody');
  const rules = await api('/rules');
  tbody.innerHTML = rules.map(rule => `
    <tr>
      <td>${rule.id}</td>
      <td>${rule.name}</td>
      <td>${rule.type}</td>
      <td>${rule.targetStatus || '-'}</td>
      <td>${rule.minApprovals ?? '-'}</td>
      <td>${rule.executionOrder}</td>
      <td>${rule.active ? 'Yes' : 'No'}</td>
    </tr>
  `).join('');
}
