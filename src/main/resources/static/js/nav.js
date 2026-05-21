/* nav.js — shared sidebar injected on every page */

function buildSidebar() {
  return `
<aside class="sidebar" id="sidebar">
  <div class="sidebar-brand">
    <div class="brand-name">MindMetrics</div>
    <div class="brand-tagline">Mental Health &amp; Burnout</div>
  </div>
  <nav class="sidebar-nav">
    <div class="nav-label">Overview</div>
    <a href="index.html" class="nav-item" data-page="index.html">
      <svg viewBox="0 0 16 16" fill="none" stroke="currentColor" stroke-width="1.5">
        <rect x="1" y="1" width="6" height="6" rx="1.5"/><rect x="9" y="1" width="6" height="6" rx="1.5"/>
        <rect x="1" y="9" width="6" height="6" rx="1.5"/><rect x="9" y="9" width="6" height="6" rx="1.5"/>
      </svg>
      Dashboard
    </a>

    <div class="nav-label" style="margin-top:6px">Data</div>
    <a href="employees.html" class="nav-item" data-page="employees.html">
      <svg viewBox="0 0 16 16" fill="none" stroke="currentColor" stroke-width="1.5">
        <circle cx="6" cy="5" r="2.5"/><circle cx="11.5" cy="5" r="2"/><path d="M1 13c0-2.2 2.2-4 5-4s5 1.8 5 4"/>
        <path d="M11.5 9c1.7 0 3 1.1 3 2.5" stroke-linecap="round"/>
      </svg>
      Employees
    </a>

    <div class="nav-label" style="margin-top:6px">Analytics</div>
    <a href="mental-health.html" class="nav-item" data-page="mental-health.html">
      <svg viewBox="0 0 16 16" fill="none" stroke="currentColor" stroke-width="1.5">
        <path d="M8 2C5.2 2 3 4 3 6.5c0 1.5.7 2.8 1.8 3.7L5 14h6l.2-3.8C12.3 9.3 13 8 13 6.5 13 4 10.8 2 8 2z"/>
      </svg>
      Mental Health
    </a>
    <a href="clinical.html" class="nav-item" data-page="clinical.html">
      <svg viewBox="0 0 16 16" fill="none" stroke="currentColor" stroke-width="1.5">
        <path d="M8 1v14M1 8h14" stroke-linecap="round"/>
        <rect x="3" y="3" width="10" height="10" rx="2"/>
      </svg>
      Clinical
    </a>
  </nav>
  <div class="sidebar-foot">Tech Workers Dataset &copy; 2025</div>
</aside>`;
}

document.addEventListener('DOMContentLoaded', () => {
  // Inject sidebar before .main
  const main = document.querySelector('.main');
  if (main) main.insertAdjacentHTML('beforebegin', buildSidebar());
  setActiveNav();
});