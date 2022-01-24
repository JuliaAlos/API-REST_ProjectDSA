$(document).ready(function() {

  let username = localStorage.getItem("username");
  console.log("Username: " + username)
  if (username==null){
  document.location.href = document.location.origin + '/notFound'
  }

  $("#product-1").hide();
  $("#product-2").hide();
  $("#product-3").hide();
  $("#product-4").hide();
  $("#product-5").hide();

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
            $("#product-2").show();
        }
        if (element.model == "Fighter"){
            $("#product-3").show();
        }
        if (element.model == "Cessna"){
            $("#product-1").show();
        }
        if (element.model == "Helicopter"){
            $("#product-4").show();
        }
        if (element.model == "Acrobatic"){
            $("#product-5").show();
        }
      });
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load airplanes')
    },
  });


});