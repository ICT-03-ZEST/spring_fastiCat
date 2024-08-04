<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<title>footer</title>
<style>
  .chatbot-button {
    position: fixed;
    bottom: 20px;
    right: 20px;
    background-color: orange;
    color: white;
    border: none;
    border-radius: 50%;
    width: 60px;
    height: 60px;
    font-size: 30px;
    text-align: center;
    cursor: pointer;
    z-index: 1000;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s, transform 0.3s;
  }

  .chatbot-button:hover {
    background-color: darkorange;
    transform: scale(1.05);
  }

  .chatbot-interface {
    position: fixed;
    bottom: 90px;
    right: 20px;
    width: 360px; /* Width increased */
    height: 400px;
    background-color: white;
    border: 2px solid orange;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    display: none;
    z-index: 1000;
    overflow: hidden;
    display: flex;
    flex-direction: column;
  }

  .chatbot-header {
    background-color: orange;
    color: white;
    padding: 10px;
    text-align: center;
    border-radius: 10px 10px 0 0;
  }

  .chatbot-body {
    flex: 1;
    padding: 10px;
    overflow-y: auto;
    display: flex;
    flex-direction: column;
    padding-bottom: 60px; /* For chatbot-footer height */
  }

  .chatbot-message {
    background-color: #f1f1f1;
    border-radius: 10px;
    padding: 10px;
    margin-bottom: 10px;
    max-width: 80%;
    word-wrap: break-word;
  }

  .chatbot-message.user {
    background-color: #dcf8c6;
    align-self: flex-end;
  }

  .chatbot-footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    display: flex;
    padding: 10px;
    border-top: 1px solid #ddd;
    background-color: white;
    box-sizing: border-box;
  }

  .chatbot-input {
    flex: 1;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-right: 10px;
    box-sizing: border-box;
  }

  .chatbot-send-button {
    background-color: orange;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 10px 15px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-right: 10px; /* Added margin-right */
  }

  .chatbot-send-button:hover {
    background-color: darkorange;
  }
</style>
<script>
  function toggleChatbot() {
    var chatbot = document.getElementById('chatbot-interface');
    if (chatbot.style.display === 'none' || chatbot.style.display === '') {
      chatbot.style.display = 'block';
    } else {
      chatbot.style.display = 'none';
    }
  }

  function sendMessage() {
    var input = document.getElementById('chatbot-input');
    var message = input.value.trim();
    if (message !== '') {
      var messageContainer = document.createElement('div');
      messageContainer.className = 'chatbot-message user';
      messageContainer.textContent = message;
      document.querySelector('.chatbot-body').appendChild(messageContainer);
      input.value = '';
      document.querySelector('.chatbot-body').scrollTop = document.querySelector('.chatbot-body').scrollHeight;
    }
  }
</script>
</head>
<body>
      <!-- footer 시작 -->
      <div class="footer_info">
	      	<div class="footer_link">
	      		<ul>
	      			<li><a href="#">회사소개</a></li>
	      			<li><a href="#">이용약관</a></li>
	      			<li><a href="#">개인정보 처리방침</a></li>
	      		</ul>
	      	</div>
	      	
		 	<div class="footer_info_customer">
		 		<h3>고객센터 02-1234-5678</h3>
		 		<p>
		 			평일 및 주말 10:00 ~ 17:00 (공휴일 휴무)<br>
		 			점심시간 12:00 ~ 13:00 
		 		</p>
	      	</div>
	      	
	      	<div class="footer_info_company">
		 		<span>법인명 : FastiCat</span>
				<span>대표 : 김준혁 </span>
				<span>사업자번호 : 123-7890</span>
				<span>사업자 소재지 : 서울시 마포구 신수동</span>
	      	</div>
      </div>
      <!-- footer 끝 -->

      <!-- Chatbot 버튼 -->
      <button class="chatbot-button" onclick="toggleChatbot()">💬</button>

      <!-- Chatbot 인터페이스 -->
      <div id="chatbot-interface" class="chatbot-interface">
        <div class="chatbot-header">AI 채팅봇</div>
        <div class="chatbot-body" id="chatbot-body">
          <div class="chatbot-message">
            안녕하세요! 무엇을 도와드릴까요?
          </div>
        </div>
        <div class="chatbot-footer">
          <input type="text" id="chatbot-input" class="chatbot-input" placeholder="메시지를 입력하세요..." onkeypress="if(event.key === 'Enter') sendMessage()">
          <button class="chatbot-send-button" onclick="sendMessage()">보내기</button>
        </div>
      </div>
</body>
</html>
