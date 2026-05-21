/* api.js — shared fetch wrappers */

const BASE = '';   // same origin; update if API runs elsewhere

async function req(method, path, body) {
  const opts = {
    method,
    headers: { 'Content-Type': 'application/json' },
  };
  if (body !== undefined) opts.body = JSON.stringify(body);
  const res = await fetch(BASE + path, opts);
  if (!res.ok) {
    const text = await res.text();
    throw new Error(text || `HTTP ${res.status}`);
  }
  if (res.status === 204) return null;
  return res.json();
}

/* Employees */
const API = {
  employees: {
    list:   (page=0,size=20)        => req('GET',`/api/employees?page=${page}&size=${size}`),
    search: (params,page=0,size=20) => {
      const q = new URLSearchParams({page,size,...Object.fromEntries(
        Object.entries(params).filter(([,v])=>v!=null&&v!=='')
      )}).toString();
      return req('GET',`/api/employees/search?${q}`);
    },
    get:    (id)     => req('GET',  `/api/employees/${id}`),
    create: (data)   => req('POST', `/api/employees`, data),
    update: (id,data)=> req('PUT',  `/api/employees/${id}`, data),
    delete: (id)     => req('DELETE',`/api/employees/${id}`),
  },

  lookup: {
    countries:   () => req('GET','/api/lookup/countries'),
    jobRoles:    () => req('GET','/api/lookup/job-roles'),
    seniority:   () => req('GET','/api/lookup/seniority-levels'),
    companySizes:() => req('GET','/api/lookup/company-sizes'),
    industries:  () => req('GET','/api/lookup/industries'),
    workModes:   () => req('GET','/api/lookup/work-modes'),
    burnoutCats: () => req('GET','/api/lookup/burnout-categories'),
    phq9Cats:    () => req('GET','/api/lookup/phq9-categories'),
    gad7Cats:    () => req('GET','/api/lookup/gad7-categories'),
  },

  analytics: {
    mentalHealthSummary:       () => req('GET','/api/analytics/mental-health-summary'),
    burnoutByIndustry:         (p) => req('GET','/api/analytics/burnout-by-industry'+(p?'?'+new URLSearchParams(p):'')),
    stressByWorkMode:          (p) => req('GET','/api/analytics/stress-by-work-mode'+(p?'?'+new URLSearchParams(p):'')),
    burnoutCategoryDistribution:(p)=> req('GET','/api/analytics/burnout-category-distribution'+(p?'?'+new URLSearchParams(p):'')),
    clinicalSummary:           () => req('GET','/api/analytics/clinical-summary'),
    phq9Distribution:          () => req('GET','/api/analytics/phq9-distribution'),
    gad7Distribution:          () => req('GET','/api/analytics/gad7-distribution'),
    highRiskEmployees:         (page=0,size=20) => req('GET',`/api/analytics/high-risk-employees?page=${page}&size=${size}`),
  },
};

/* Toast helper */
function toast(msg, type='success') {
  let c = document.getElementById('toast-container');
  if (!c) { c = document.createElement('div'); c.id = 'toast-container'; document.body.appendChild(c); }
  const t = document.createElement('div');
  t.className = `toast ${type}`;
  t.textContent = msg;
  c.appendChild(t);
  setTimeout(() => t.remove(), 3200);
}

/* Populate a <select> from a lookup array */
function populateSelect(sel, items, valKey, labelKey, placeholder='Select…') {
  sel.innerHTML = `<option value="">${placeholder}</option>` +
    items.map(i => `<option value="${i[valKey]}">${i[labelKey]}</option>`).join('');
}

/* Mark active nav link based on current page */
function setActiveNav() {
  const page = location.pathname.split('/').pop() || 'index.html';
  document.querySelectorAll('.nav-item[data-page]').forEach(el => {
    el.classList.toggle('active', el.dataset.page === page);
  });
}