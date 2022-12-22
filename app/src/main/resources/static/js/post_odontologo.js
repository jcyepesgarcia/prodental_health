window.addEventListener("load", function () {
  const formulario = document.querySelector("#add_new_odontologo");

  //Ante un submit del formulario se ejecutará la siguiente funcion
  formulario.addEventListener("submit", function (event) {
    //JSON con los datos del odontologo
    const formData = {
      matricula: document.querySelector("#matricula").value,
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
    };
    //función fetch con el método POST para guardar un odontologo.
    const url = "/odontologos";
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
    document.querySelector("#matricula").value = "";
    document.querySelector("#nombre").value = "";
    document.querySelector("#apellido").value = "";
  }
});
