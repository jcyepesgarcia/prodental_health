window.addEventListener("load", function () {
  const formulario = document.querySelector("#add_new_paciente");

  //Ante un submit del formulario se ejecutará la siguiente funcion
  formulario.addEventListener("submit", function (event) {
    //JSON con los datos del paciente
    const formData = {
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      cedula: document.querySelector("#cedula").value,
      email: document.querySelector("#email").value,
      fechaIngreso: document.querySelector("#fechaIngreso").value,
      domicilio: {
        calle: document.querySelector("#calle").value,
        numero: document.querySelector("#numero").value,
        localidad: document.querySelector("#localidad").value,
        provincia: document.querySelector("#provincia").value,
      },
    };
    //función fetch con el método POST para guardar un paciente.
    const url = "/pacientes";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        resetUploadForm();
      })
      .catch((error) => {
        console.log(error);
        resetUploadForm();
      });
  });

  function resetUploadForm() {
    document.querySelector("#nombre").value = "";
    document.querySelector("#apellido").value = "";
    document.querySelector("#cedula").value = "";
    document.querySelector("#email").value = "";
    document.querySelector("#fechaIngreso").value = "";
    document.querySelector("#calle").value = "";
    document.querySelector("#numero").value = "";
    document.querySelector("#localidad").value = "";
    document.querySelector("#provincia").value = "";
  }
});
