// Функция для отображения соответствующего раздела
function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => section.style.display = 'none');
    document.getElementById(sectionId).style.display = 'block';
}

function setActiveLink(link) {
    const links = document.querySelectorAll('.sidebar a');
    links.forEach(link => link.classList.remove('active')); // Убираем класс active со всех ссылок
    link.classList.add('active'); // Добавляем класс active на текущую ссылку
    localStorage.setItem('activeLink', link.textContent); // Сохраняем активную ссылку в localStorage
}

function restoreActiveLink() {
    const activeLinkText = localStorage.getItem('activeLink');
    if (activeLinkText) {
    const links = document.querySelectorAll('.sidebar a');
    links.forEach(link => {
    if (link.textContent === activeLinkText) {
    link.classList.add('active');
    showSection(link.getAttribute('onclick').split("'")[1]); // Открываем соответствующий раздел
}
});
}
}


(function () { 'use strict'
    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
    .forEach(function (form) {
    form.addEventListener('submit', function (event) {
    if (!form.checkValidity()) {
    event.preventDefault()
    event.stopPropagation()
}
    form.classList.add('was-validated')
}, false)
})
})()


document.addEventListener('DOMContentLoaded', restoreActiveLink);
