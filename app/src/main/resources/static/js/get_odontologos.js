window.addEventListener('load', function () {
  (function(){

    const url = '/odontologos';
    const settings = {
      method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
       for(odonto of data){
          //por cada odontologo se crea una fila en la tabla
          //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontologo
          var table = document.getElementById("odontologoTableBody");
          var odontologoRow =table.insertRow();
          let tr_id = 'tr_' + odonto.id;
          odontologoRow.id = tr_id;

          //por cada odontologo se crea un boton delete para poder eliminar el mismo
          //dicho boton invoca la funcion de deleteBy(id) que se encargará
          //de llamar a la API para eliminar un odontologo
          let deleteButton = `<button id="btn_delete_${odonto.id}" type="button" onclick="deleteBy(${odonto.id})" class="btn btn-danger" title="Eliminar"><i class="bi bi-trash"></i></button>`;

          //por cada odontologo se crea un boton que muestra el id y que al hacerle clic invoca
          //la función findBy(id) que se encargará de buscar el odontologo y de hacer una solicitud PUT a la API
          let updateButton = `<button id="btn_id_${odonto.id}" type="button" onclick="findBy(${odonto.id})" class="btn btn-warning" title="Actualizar"><i class="bi bi-pencil-square"></i></button>`;

          odontologoRow.innerHTML = '<td>' + odonto.id + '</td>' +
                  '<td class=\"td_matricula\">' + odonto.matricula + '</td>' +
                  '<td class=\"td_nombre\">' + odonto.nombre.toUpperCase() + '</td>' +
                  '<td class=\"td_apellido\">' + odonto.apellido.toUpperCase() + '</td>' +
                  '<td>' + updateButton + '</td>'+
                  '<td>' + deleteButton + '</td>';
      };

  })
  })

  (function(){
    let pathname = window.location.pathname;
    if (pathname == "/get_odontologos.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })

});