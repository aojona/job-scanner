function toggleMobileMenu(menu) {
    menu.classList.toggle('open');
    document.body.style.overflow = document.body.style.overflow === 'hidden'
        ? 'visible'
        : 'hidden';
}