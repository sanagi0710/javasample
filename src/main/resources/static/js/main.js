document.addEventListener('DOMContentLoaded', () => {

	const list = document.querySelector(".list-group");
	// CSRFトークン取得関数
	const getCsrfToken = () => document.querySelector('input[name="${_csrf.parameterName}"]').value;

	document.getElementById('deleteAllButton').addEventListener('click', function() {
		;
		fetch('/delete', {
			method: 'POST',
		})
			.then(response => {
				if (response.ok) {
					console.log('削除成功');
					list.remove();
				} else {
					console.log('削除失敗');
				}
			})
			.catch(error => {
				console.error('Error:', error);
			});
	});

	document.addEventListener('DOMContentLoaded', () => {
		// 全ての削除ボタンを取得
		document.querySelectorAll('.deleteOneButton').forEach(button => {
			button.addEventListener('click', function() {
				const messageId = this.getAttribute('data-id');

				// 削除リクエストを送信
				fetch(`/delete/${messageId}`, {
					method: 'DELETE',
				})
					.then(response => {
						if (response.ok) {
							console.log('削除成功');
							// 削除成功後、DOMからメッセージを削除
							this.closest('li').remove();
						} else {
							console.log('削除失敗');
						}
					})
					.catch(error => {
						console.error('エラー:', error);
					});
			});
		});
	});

	// 編集ボタンのクリックイベント
	document.querySelectorAll('.editButton').forEach(button => {
		button.addEventListener('click', function() {
			const messageId = this.getAttribute('data-id');

			const parentLi = this.closest('li'); // li 要素を取得
			const spanElements = parentLi.querySelectorAll('span');

			const messageContent = spanElements[1].textContent;

			// モーダルにメッセージ内容を設定
			document.getElementById('editMessageId').value = messageId;
			document.getElementById('editMessageContent').value = messageContent;

			// モーダルを表示
			const editModal = new bootstrap.Modal(document.getElementById('editModal'));
			editModal.show();
		});
	});

	// 保存ボタンのクリックイベント
	document.getElementById('saveEditButton').addEventListener('click', function() {
		const messageId = document.getElementById('editMessageId').value;
		const updatedContent = document.getElementById('editMessageContent').value;

		// AJAXリクエストでメッセージを更新（サーバーサイドの実装が必要）
		fetch(`/update/${messageId}`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'X-CSRF-TOKEN': getCsrfToken()
			},
			body: JSON.stringify({ content: updatedContent })
		})
			.then(response => {
				if (response.ok) {
					// ページをリロードして更新を反映
					location.reload();
				} else {
					console.error('エラーが発生しました。');
				}
			});
	});
});

