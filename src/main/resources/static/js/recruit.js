
// Swiperの初期化
const swiper = new Swiper('.swiper', {
	slidesPerView: 5, // 1行あたりのスライド数
	spaceBetween: 2, // スライド間の余白
	pagination: {
		el: '.swiper-pagination',
		clickable: true,
	},
	navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev',
	},
	scrollbar: {
		el: '.swiper-scrollbar',
		hide: true,
	},
	breakpoints: {
		// 画面サイズに応じたスライド数を設定
		640: {
			slidesPerView: 1,
		},
		768: {
			slidesPerView: 2,
		},
		1024: {
			slidesPerView: 3,
		},
	},
});

const list = document.querySelector(".topic__list");
const items = document.querySelectorAll(".topic__item");
const totalWidth = Array.from(items).reduce((sum, item) => sum + item.clientWidth, 0);

list.style.animation = `scroll ${totalWidth / 100}s linear infinite`;

