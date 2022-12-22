window.addEventListener("load", function () {
  document.querySelector(".nickName").focus();

  //Se crea esta variable para agregar un tiempo de espera en el evento 'keydown' con el método setTimeout(), y que el evento no recoja de uno en uno los carácteres digitados por el ususario.
  let timeout = null;

  //Expresión regular para la validación de estructura de un email.
  const mailFormatRegex = /^[^@]+@\w+(\.\w+)+\w$/;

  document.querySelectorAll(".form-box").forEach((form_box) => {
    let boxInput = form_box.querySelector("input");

    boxInput.addEventListener("keydown", () => {
      clearTimeout(timeout);

      //El usuario tiene un tiempo de espera de 0.5 seg una vez se detiene al digitar, antes de que el evento se reinicie.
      timeout = setTimeout(() => {
        validation(form_box, boxInput);
      }, 500);
    });
  });

  //La función valida si el ususario ingresa información en los input.
  //Si no lo hace agrega la clase .form-error al div form_box.
  //Si lo hace agrega la clase .form-success al div form_box.
  validation = (form_box, boxInput) => {
    //En el else se hace una validación genérica si el usuario deja un ítem sin diligenciar.
    if (boxInput.value == "") {
      showErrorOrSuccess(true, form_box, boxInput);
    } else {
      showErrorOrSuccess(false, form_box, boxInput);
    }
    //Validación de la estructura de un email.
    if (boxInput.name == "email") {
      if (!boxInput.value.match(mailFormatRegex)) {
        showErrorOrSuccess(true, form_box, boxInput);
      } else {
        showErrorOrSuccess(false, form_box, boxInput);
      }
    }
  };

  //Función de errores y validación.
  //Agrega o elimina las clases .form-success y .form-error dependiendo del caso evaluado.
  showErrorOrSuccess = (check, form_box, boxInput) => {
    if (check) {
      form_box.classList.remove("form-success");
      form_box.classList.add("form-error");
    } else {
      form_box.classList.remove("form-error");
      form_box.classList.add("form-success");
    }
  };
});
