document.addEventListener('DOMContentLoaded', () => {

  // Theme Toggle Logic
  const themeToggleBtn = document.getElementById('theme-toggle');
  const storedTheme = localStorage.getItem('theme');

  if (storedTheme) {
    document.documentElement.setAttribute('data-theme', storedTheme);
  } else if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
    document.documentElement.setAttribute('data-theme', dark);
  }

  themeToggleBtn.addEventListener('click', () => {
    const currentTheme = document.documentElement.getAttribute('data-theme');
    const targetTheme = currentTheme === 'dark' ? 'light' : 'dark';
    
    document.documentElement.setAttribute('data-theme', targetTheme);
    localStorage.setItem('theme', targetTheme);
  });

  // Mobile Menu Navigation Toggle
  const hamburger = document.getElementById('hamburger');
  const navMenu = document.getElementById('nav-menu');

  hamburger.addEventListener('click', () => {
    navMenu.classList.toggle('active');
  });

  document.querySelectorAll('.nav-center a').forEach(link => {
    link.addEventListener('click', () => {
      navMenu.classList.remove('active');
    });
  });

  // Dynamic Skill Hover Description
  const skillItems = document.querySelectorAll('.skill-item');
  const hoverCard = document.getElementById('skill-hover-card');

  skillItems.forEach(item => {
    item.addEventListener('mouseenter', () => {
      const desc = item.getAttribute('data-desc');
      if (desc) {
        hoverCard.textContent = desc;
        hoverCard.style.color = 'var(--text-primary)';
      }
    });

    item.addEventListener('mouseleave', () => {
      hoverCard.textContent = 'Select or hover over a technology to view details.';
      hoverCard.style.color = 'var(--text-muted)';
    });
  });

  // Intersection Observer for Subtle Scroll Reveal Animations
  const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
  };

  const revealObserver = new IntersectionObserver((entries, observer) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.style.opacity = '1';
        entry.target.style.transform = 'translateY(0)';
        observer.unobserve(entry.target);
      }
    });
  }, observerOptions);

  const sectionsToAnimate = document.querySelectorAll('section, .project-layout, .grid-item');
  sectionsToAnimate.forEach(el => {
    el.style.opacity = '0';
    el.style.transform = 'translateY(20px)';
    el.style.transition = 'opacity 0.8s ease, transform 0.8s ease';
    revealObserver.observe(el);
  });
});
