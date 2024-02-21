$('.slider').slick({
    autoplay: true,
    speed: 3000,
    slidesToShow: 1,
    slidesToScroll: 1,
    prevArrow: '<button type="button" class="slick-prev btn-custom-prev"><i class="fa-solid fa-arrow-left-long"></i></button>',
    nextArrow: '<button type="button" class="slick-next btn-custom-next"><i class="fa-solid fa-arrow-right-long"></i></button>'
});

    
$('.list-bottom').slick({
    autoplay: true,
    speed: 2000,
    slidesToShow: 5,
    slidesToScroll: 1,
    prevArrow: '<button type="button" class="slick-prev btn-custom-prev"><i class="fa-solid fa-chevron-left"></i></button>',
    nextArrow: '<button type="button" class="slick-next btn-custom-next"><i class="fa-solid fa-chevron-right"></i></button>',
});

$('.list-item').slick({
    autoplay: true,
    speed: 2000,
    slidesToShow: 5,
    slidesToScroll: 1,
    prevArrow: '<button type="button" class="slick-prev btn-custom-prev"><i class="fa-solid fa-chevron-left"></i></button>',
    nextArrow: '<button type="button" class="slick-next btn-custom-next"><i class="fa-solid fa-chevron-right"></i></button>',
});

new Glider(document.querySelector('.glider'), {
    slidesToShow: 5,
    slidesToScroll: 1,
    draggable: true,
    arrows: {
      prev: '.glider-prev',
      next: '.glider-next',
    }
  });

new Glider(document.querySelector('.glider1'), {
    slidesToShow: 5,
    slidesToScroll: 1,
    draggable: true,
    arrows: {
      prev: '.glider-prev1',
      next: '.glider-next1',
    }
  });

new Glider(document.querySelector('.glider2'), {
    slidesToShow: 5,
    slidesToScroll: 1,
    draggable: true,
    arrows: {
      prev: '.glider-prev2',
      next: '.glider-next2',
    }
  });

// chuyển sản phẩm
$('.p-slider').hide();
$('.p-slider:first-child').fadeIn(); 

// Xử lý sự kiện khi nhấp vào tab
$('.controls li').click(function () {
    $('.controls li').removeClass('active');
    $(this).addClass('active');
    let id_tab_content = $(this).children('a').attr('href');
    $('.p-slider').fadeOut(1); 
    $(id_tab_content).fadeIn(1);
    return false;
});
