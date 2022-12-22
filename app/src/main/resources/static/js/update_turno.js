window.addEventListener("load", function () {
  const formulario = document.querySelector("#update_turno_form");

  formulario.addEventListener("submit", function (event) {
    //creamos un JSON con los datos del turno.
    const formData = {
      id: document.querySelector("#turnoId").value,
      pacienteId: document.querySelector("#pacienteId").value,
      odontologoId: document.querySelector("#odontologoId").value,
      fecha: document.querySelector("#fecha").value,
    };

    //función fetch con el método PUT para actualizar un turno.
    const url = "/turnos";
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    fetch(url, settings).then((response) => {
      alert("El registro ha sido actualizado con éxito")
      response.json()
    });
  });
});

//La funcion se encarga de llenar el formulario con los datos del turno que se desea modificar.
function findBy(id) {
  const url = "/turnos" + "/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let turno = data;
      document.querySelector("#turnoId").value = turno.id;
      document.querySelector("#pacienteId").value = turno.pacienteId;
      document.querySelector("#odontologoId").value = turno.odontologoId;
      document.querySelector("#fecha").value = turno.fecha;

      //el formulario por default esta oculto y al editar se habilita
      document.querySelector("#div_turno_updating").classList.toggle("hidden");
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}
