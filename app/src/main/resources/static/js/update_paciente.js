window.addEventListener("load", function () {
  const formulario = document.querySelector("#update_paciente_form");

  formulario.addEventListener("submit", function (event) {
    //creamos un JSON con los datos del paciente.
    const formData = {
      id: document.querySelector("#paciente_id").value,
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      cedula: document.querySelector("#cedula").value,
      fechaIngreso: document.querySelector("#fechaIngreso").value,
      email: document.querySelector("#email").value,
      domicilio: {
        id: document.querySelector("#domicilio_id").value,
        calle: document.querySelector("#calle").value,
        numero: document.querySelector("#numero").value,
        localidad: document.querySelector("#localidad").value,
        provincia: document.querySelector("#provincia").value,
      },
    };

    //función fetch con el método PUT para actualizar un paciente.
    const url = "/pacientes";
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

//La funcion se encarga de llenar el formulario con los datos del paciente que se desea modificar.
function findBy(id) {
  const url = "/pacientes" + "/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let paciente = data;
      document.querySelector("#paciente_id").value = paciente.id;
      document.querySelector("#nombre").value = paciente.nombre;
      document.querySelector("#apellido").value = paciente.apellido;
      document.querySelector("#cedula").value = paciente.cedula;
      document.querySelector("#fechaIngreso").value = paciente.fechaIngreso;
      document.querySelector("#email").value = paciente.email;
      document.querySelector("#domicilio_id").value = paciente.domicilio.id;
      document.querySelector("#calle").value = paciente.domicilio.calle;
      document.querySelector("#numero").value = paciente.domicilio.numero;
      document.querySelector("#localidad").value = paciente.domicilio.localidad;
      document.querySelector("#provincia").value = paciente.domicilio.provincia;

      //el formulario por default esta oculto y al editar se habilita.
      document
        .querySelector("#div_paciente_updating")
        .classList.toggle("hidden");
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}
