window.addEventListener("load", function () {
  const formulario = document.querySelector("#add_new_turno");

  //Ante un submit del formulario se ejecutará la siguiente funcion.
  formulario.addEventListener("submit", function (event) {
    //JSON con los datos del turno.
    const formData = {
      pacienteId: document.querySelector("#pacienteId").value,
      odontologoId: document.querySelector("#odontologoId").value,
      fecha: document.querySelector("#fecha").value,
    };
    //función fetch con el método POST para guardar un turno.
    const url = "/turnos";
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
    document.querySelector("#pacienteId").value = "";
    document.querySelector("#odontologoId").value = "";
    document.querySelector("#fecha").value = "";
  }
});
