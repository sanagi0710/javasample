document.addEventListener('DOMContentLoaded', () => {

	const initializeSwiper = () => {
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
	}

	// ブックマークフラグ更新処理
	const updateBookmarkFlag = () => {
		const bookmarkCheckbox = document.getElementById("bookmarkCheckbox");
		const bookmarkFlog = document.getElementsByName("bookmarkFlag")[0];

		if (bookmarkCheckbox.checked) {
			bookmarkFlog.value = "true";
		} else {
			bookmarkFlog.value = "false";
		}
	};


	const showNextItem = () => {
		// 全取得
		const items = document.querySelectorAll(".topic__item");
		let currentIndex = 0;

		const showNext = () => {
			items[currentIndex].style.display = "none";
			items[currentIndex].style.zIndex = 98;

			currentIndex = (currentIndex + 1) % items.length;

			items[currentIndex].style.display = "flex";
			items[currentIndex].style.zIndex = 99;
			items[currentIndex].style.opacity = 1;
		}

		setInterval(showNext(), 2000);

	};


	// アニメーション流す処理
	const playAnimation = () => {
		const list = document.querySelector(".topic__list");
		const items = document.querySelectorAll(".topic__item");
		const totalWidth = Array.from(items).reduce((sum, item) => sum + item.clientWidth, 0);

		list.style.animation = `scroll ${totalWidth / 100}s linear infinite`;
	}

	// 全初期化処理
	const initialize = () => {
		initializeSwiper();
		updateBookmarkFlag();
		playAnimation();
	}

	initialize();

});



