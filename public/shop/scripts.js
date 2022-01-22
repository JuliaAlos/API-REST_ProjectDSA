$(document).ready(function() {

  let username = localStorage.getItem("username");
  console.log("Username: " + username)
  if (username==null){
    document.location.href = document.location.origin + '/notFound'
  }

  $.ajax({
      type: "GET",
      url: `/dsaApp/user/getByMoney/${username}`,
      contentType: "application/json",
      success: function (data) {
        console.log(data);
        $("#money").text(`  Bitcoins: ${data.score}`);
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log('Could not load bitcoins ranking')
      },
    });

  $("#logout").click(function (){
    $.ajax({
      type: "GET",
      url: `/dsaApp/user/logout/${username}`,
      contentType: "application/json",
      success: function (data) {
        console.log('User logged out');
        localStorage.clear();
        document.location.href = document.location.origin + '/'


      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log('Could not log out')
      },
    });
  });


  $.ajax({
    type: "GET",
    url: `/dsaApp/planes/getListPlanesPlayer/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $.each(data, function(index, element){
        if (element.model == "Airbus"){
            $("#airbus").remove();
            $("#airbusT").remove();
        }
        if (element.model == "Fighter"){
            $("#fighter").remove();
            $("#fighterT").remove();
        }
        if (element.model == "Cessna"){
            $("#cessna").remove();
            $("#cessnaT").remove();
        }
        if (element.model == "Helicopter"){
            $("#helicopter").remove();
            ("#helicopterT").remove();
        }
        if (element.model == "Acrobatic"){
            $("#acrobatic").remove();
            $("#acrobaticT").remove();
        }
      });
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load distance ranking')
    },
  });

  $("#cessna").click(function (){
      $.ajax({
        type: "POST",
        url: `/dsaApp/planes/addPlaneToPlayer`,
        contentType: "application/json",
        data: JSON.stringify({playerName: username, planeModel: "Cessna"}),
        success: function (data) {
          console.log('Buy cessna');
          document.location.href = document.location.origin + '/shop/'

        },
        error: function (xhr, ajaxOptions, thrownError) {
          console.log('Could not buy')
          window.alert(error)
        },
      });
  });

  $("#airbus").click(function (){
      $.ajax({
        type: "POST",
        url: `/dsaApp/planes/addPlaneToPlayer`,
        contentType: "application/json",
        data: JSON.stringify({playerName: username, planeModel: "Airbus"}),
        success: function (data) {
          console.log('Buy airbus');
          document.location.href = document.location.origin + '/shop/'

        },
        error: function (xhr, ajaxOptions, thrownError) {
          console.log('Could not buy')
          window.alert(error)
        },
      });
  });

  $("#fighter").click(function (){
      $.ajax({
        type: "POST",
        url: `/dsaApp/planes/addPlaneToPlayer`,
        contentType: "application/json",
        data: JSON.stringify({playerName: username, planeModel: "Fighter"}),
        success: function (data) {
          console.log('Buy fighter');
          document.location.href = document.location.origin + '/shop/'

        },
        error: function (xhr, ajaxOptions, thrownError) {
          console.log('Could not buy')
          window.alert(error)
        },
      });
  });

  $("#helicopter").click(function (){
      $.ajax({
        type: "POST",
        url: `/dsaApp/planes/addPlaneToPlayer`,
        contentType: "application/json",
        data: JSON.stringify({playerName: username, planeModel: "Helicopter"}),
        success: function (data) {
          console.log('Buy helicopter');
          document.location.href = document.location.origin + '/shop/'

        },
        error: function (xhr, ajaxOptions, thrownError) {
          console.log('Could not buy')
          window.alert(error)
        },
      });
  });

  $("#acrobatic").click(function (){
      $.ajax({
        type: "POST",
        url: `/dsaApp/planes/addPlaneToPlayer`,
        contentType: "application/json",
        data: JSON.stringify({playerName: username, planeModel: "Acrobatic"}),
        success: function (data) {
          console.log('Buy acrobatic');
          document.location.href = document.location.origin + '/shop/'

        },
        error: function (xhr, ajaxOptions, thrownError) {
          console.log('Could not buy')
          window.alert(error)
        },
      });
  });


});