// Toggle de modo noche/día - Script inmediato (sin esperar DOMContentLoaded)
(function() {
    const htmlElement = document.documentElement;
    
    // Cargar tema guardado en localStorage de forma inmediata
    const savedTheme = localStorage.getItem('theme') || 'light';
    htmlElement.setAttribute('data-bs-theme', savedTheme);
    
    // Agregar clase para transición suave
    htmlElement.style.transition = 'none';
})();

document.addEventListener('DOMContentLoaded', function() {
    // Permitir transiciones después de cargar el DOM
    const htmlElement = document.documentElement;
    htmlElement.style.transition = '';
    
    // Buscar ambos toggles (fijo y en navbar)
    const themeToggleFixed = document.getElementById('theme-toggle');
    const themeToggleNavbar = document.querySelector('.theme-toggle-navbar');
    
    const currentTheme = htmlElement.getAttribute('data-bs-theme');
    updateThemeIcon(themeToggleFixed, currentTheme);
    updateThemeIcon(themeToggleNavbar, currentTheme);
    
    // Evento del toggle fijo
    if (themeToggleFixed) {
        themeToggleFixed.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            toggleTheme();
        });
    }
    
    // Evento del toggle en navbar
    if (themeToggleNavbar) {
        themeToggleNavbar.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            toggleTheme();
        });
    }
});

function toggleTheme() {
    const htmlElement = document.documentElement;
    const currentTheme = htmlElement.getAttribute('data-bs-theme');
    const newTheme = currentTheme === 'light' ? 'dark' : 'light';
    
    // Aplicar cambio de tema inmediatamente
    htmlElement.setAttribute('data-bs-theme', newTheme);
    localStorage.setItem('theme', newTheme);
    
    // Disparar evento personalizado para que otros scripts reaccionen
    window.dispatchEvent(new CustomEvent('themeChanged', { detail: { theme: newTheme } }));
    
    // Actualizar ambos toggles
    const themeToggleFixed = document.getElementById('theme-toggle');
    const themeToggleNavbar = document.querySelector('.theme-toggle-navbar');
    
    updateThemeIcon(themeToggleFixed, newTheme);
    updateThemeIcon(themeToggleNavbar, newTheme);
}

function updateThemeIcon(toggle, theme) {
    if (!toggle) return;
    
    const icon = toggle.querySelector('i');
    if (!icon) return;
    
    if (theme === 'dark') {
        icon.classList.remove('fa-moon');
        icon.classList.add('fa-sun');
        toggle.title = 'Cambiar a modo claro';
        toggle.setAttribute('aria-label', 'Cambiar a modo claro');
    } else {
        icon.classList.remove('fa-sun');
        icon.classList.add('fa-moon');
        toggle.title = 'Cambiar a modo oscuro';
        toggle.setAttribute('aria-label', 'Cambiar a modo oscuro');
    }
}
