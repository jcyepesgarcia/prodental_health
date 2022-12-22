window.addEventListener('load', function () {
  (function(){

    const url = '/turnos';
    const settings = {
      method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
       for(turno of data){
          //por cada odontologo se crea una fila en la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontologo
          var table = document.getElementById("turnoTableBody");
          var turnoRow =table.insertRow();
          let tr_id = 'tr_' + turno.id;
          turnoRow.id = tr_id;

          //por cada turno se crea un boton delete para poder eliminar el mismo
          //dicho boton invoca la funcion de deleteBy(id) que se encargará
          //de llamar a la API para eliminar un turno
          let deleteButton = `<button id="btn_delete_${turno.id}" type="button" onclick="deleteBy(${turno.id})" class="btn btn-danger" title="Eliminar"><i class="bi bi-trash"></i></button>`;

          //por cada turno se crea un boton que muestra el id y que al hacerle clic invoca
          //la función findBy(id) que se encargará de buscar el turno y de hacer una solicitud PUT a la API
          let updateButton = `<button id="btn_id_${turno.id}" type="button" onclick="findBy(${turno.id})" class="btn btn-warning" title="Actualizar"><i class="bi bi-pencil-square"></i></button>`;

          turnoRow.innerHTML = '<td>' + turno.id + '</td>' +
                  '<td class=\"td_nombre\">' + turno.pacienteId + '</td>' +
                  '<td class=\"td_apellido\">' + turno.odontologoId + '</td>' +
                  '<td class=\"td_fechaIngreso\">' + turno.fecha + '</td>' +
                  '<td>' + updateButton + '</td>'+
                  '<td>' + deleteButton + '</td>';
      };

  })
  })

  (function(){
    let pathname = window.location.pathname;
    if (pathname == "/get_turnos.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })

});