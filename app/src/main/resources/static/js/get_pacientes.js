window.addEventListener('load', function () {
  (function(){

    const url = '/pacientes';
    const settings = {
      method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        for(paciente of data){
          //por cada paciente se crea una fila en la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el paciente
          var table = document.getElementById("pacienteTableBody");
          var pacienteRow =table.insertRow();
          let tr_id = 'tr_' + paciente.id;
          pacienteRow.id = tr_id;

          //por cada paciente se crea un boton delete para poder eliminar el mismo
          //dicho boton invoca la funcion de deleteBy(id) que se encargará
          //de llamar a la API para eliminar un paciente
          let deleteButton = `<button id="btn_delete_${paciente.id}" type="button" onclick="deleteBy(${paciente.id})" class="btn btn-danger" title="Eliminar"><i class="bi bi-trash"></i></button>`;

          //por cada paciente se crea un boton que muestra el id y que al hacerle clic invoca
          //la función findBy(id) que se encargará de buscar el paciente y de hacer una solicitud PUT a la API
          let updateButton = `<button id="btn_id_${paciente.id}" type="button" onclick="findBy(${paciente.id})" class="btn btn-warning" title="Actualizar"><i class="bi bi-pencil-square"></i></button>`;


          pacienteRow.innerHTML = '<td>' + paciente.id + '</td>' +
          '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
          '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
          '<td class=\"td_cedula\">' + paciente.cedula + '</td>' +
          '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso + '</td>' +
          '<td class=\"td_email\">' + paciente.email.toUpperCase() + '</td>' +
          '<td>' + updateButton + '</td>'+
          '<td>' + deleteButton + '</td>';
      };

  })
  })

  (function(){
    let pathname = window.location.pathname;
    if (pathname == "/get_pacientes.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })

})