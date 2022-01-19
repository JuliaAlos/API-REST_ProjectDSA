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



  $(window).scroll(function () {
    if (this.scrollY > 20) {
      $(".navbar").addClass("sticky");
      $(".goTop").fadeIn();
    } else {
      $(".navbar").removeClass("sticky");
      $(".goTop").fadeOut();
    }
  });

  $(".goTop").click(function () {
    scroll(0, 0)
  });

  $('.menu-toggler').click(function () {
    $(this).toggleClass("active");
    $(".navbar-menu").toggleClass("active");
  });

  $(".works").magnificPopup({
    delegate: 'a',
    type: 'image',
    gallery: {enabled: true}
  });


  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByDistance`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);

      $.each(data, function (index, element) {
        if(index<10){
          let $tr = $("<tr>").append(
              $("<td>").text(index+1),
              $("<td>").text(
                  element.userName
              ),
              $("<td>").text(element.score)
          );

          $("#distanceTable").children("tbody").append($tr)
        }
      });
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load distance ranking')
    },
  });

  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByDistance/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $("#distanceText").text(`Your position in the leader board is ${data.pos} with ${data.score} m!`);
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load distance ranking')
    },
  });

  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByTime`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);


      $.each(data, function (index, element) {
        if(index<10){
          let $tr = $("<tr>").append(
              $("<td>").text(index+1),
              $("<td>").text(
                  element.userName
              ),
              $("<td>").text(element.score)
          );

        $("#timeTable").children("tbody").append($tr)
        }
      });

    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load time ranking')
    },
  });

  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByTime/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $("#timeText").text(`Your position in the leader board is ${data.pos} with ${data.score} h!`);
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load time ranking')
    },
  });

  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByMoney`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);


      $.each(data, function (index, element) {
        if(index<10){
          let $tr = $("<tr>").append(
              $("<td>").text(index+1),
              $("<td>").text(
                  element.userName
              ),
              $("<td>").text(element.score)
          );

          $("#bitcoinsTable").children("tbody").append($tr)
        }
      });

    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load bitcoins ranking')
    },
  });

  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByMoney/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $("#moneyText").text(`Your position in the leader board is ${data.pos} with ${data.score} bitcoins!`);
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load bitcoins ranking')
    },
  });

  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByRol`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);


      $.each(data, function (index, element) {
        if(index<10){
          let $tr = $("<tr>").append(
              $("<td>").text(index+1),
              $("<td>").text(
                  element.userName
              ),
              $("<td>").text(element.score)
          );

          $("#rolTable").children("tbody").append($tr)
        }

      });
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load rol ranking')
    },
  });

  $.ajax({
    type: "GET",
    url: `/dsaApp/user/getByRol/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $("#rolText").text(`Your position in the leader board is ${data.pos} by being a ${data.score}!`);
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load rol ranking')
    },
  });


});

