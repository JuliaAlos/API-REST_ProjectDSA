$(document).ready(function() {

  let username = localStorage.getItem("username");
  console.log("Username: " + username)

  $("#logout").click(function (){
    $.ajax({
      type: "GET",
      url: `/dsaApp/user/logout/${username}`,
      contentType: "application/json",
      success: function (data) {
        console.log('User logged out');
        document.location.href = document.location.origin + '/'

      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log('Could not log out')
      },
    });
  })


  $.ajax({
    type: "GET",
    url: `/dsaApp/planes/getListPlanesPlayer/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $.each(json, function(index, element){
        if (element.model == "Airbus"){
            $("#airbus").remove();
        }
        if (element.model == "Fighter"){
            $("#fighter").remove();
        }
        if (element.model == "Cessna"){
            $("#cessna").remove();
        }
        if (element.model == "Helicopter"){
            $("#helicopter").remove();
        }
        if (element.model == "Acrobatic"){
            $("#acrobatic").remove();
        }
      });
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load distance ranking')
    },
  });

});