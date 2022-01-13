$(document).ready(function() {
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


});

