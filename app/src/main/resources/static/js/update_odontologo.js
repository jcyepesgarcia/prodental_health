window.addEventListener("load", function () {
  const formulario = document.querySelector("#update_odontologo_form");

  formulario.addEventListener("submit", function (event) {
    //creamos un JSON con los datos del odontólogo.
    const formData = {
      id: document.querySelector("#odontologo_id").value,
      matricula: document.querySelector("#matricula").value,
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
    };
    console.log(formData);

    //función fetch con el método PUT para actualizar un odontólogo.
    const url = "/odontologos";
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };
    fetch(url, settings).then((response) => {
      alert("El registro ha sido actualizado con éxito")
      response.json()});
  });
});

//La funcion se encarga de llenar el formulario con los datos del odontólogo que se desea modificar.
function findBy(id) {
  const url = "/odontologos" + "/" + id;
  const settings = {
    method: "GET",
  };
  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let odontologo = data;
      document.querySelector("#odontologo_id").value = odontologo.id;
      document.querySelector("#matricula").value = odontologo.matricula;
      document.querySelector("#nombre").value = odontologo.nombre;
      document.querySelector("#apellido").value = odontologo.apellido;

      //el formulario por default esta oculto y al editar se habilita.
      document
        .querySelector("#div_odontologo_updating")
        .classList.toggle("hidden");
    })
    .catch((error) => {
      alert("Error: " + error);
    });
}
