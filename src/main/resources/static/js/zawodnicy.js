// Populate form for editing
function populateForm(button) {
    const row = button.closest("tr");
    document.getElementById("formTitle").innerText = "Edit Athlete";
    document.getElementById("athleteId").value = row.children[0].innerText.trim();
    document.getElementById("imie").value = row.children[1].innerText.trim();
    document.getElementById("nazwisko").value = row.children[2].innerText.trim();
    document.getElementById("numerTelefonu").value = row.children[3].innerText.trim();
    document.getElementById("email").value = row.children[4].innerText.trim();
    document.getElementById("oplataSubskrypcyjna").value = row.children[5].innerText.trim();
    document.getElementById("athleteForm").action = `/zawodnicy/update/${row.children[0].innerText.trim()}`;
}

// Reset form for adding a new athlete
function resetForm() {
    document.getElementById("formTitle").innerText = "Add Athlete";
    document.getElementById("athleteId").value = "";
    document.getElementById("imie").value = "";
    document.getElementById("nazwisko").value = "";
    document.getElementById("numerTelefonu").value = "";
    document.getElementById("email").value = "";
    document.getElementById("oplataSubskrypcyjna").value = "";
    document.getElementById("athleteForm").action = "/zawodnicy/add";
}
