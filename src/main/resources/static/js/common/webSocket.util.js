/**
 * File Name   : webSocket.util.js
 * Description : 웹소켓 유틸 자바스크립트
 * Author      : nana
 * Date        : 2018.11.06
 */

var WebSocketUtil = {

    /**
     * 초기화
     */
    init: function(onMessageCallback) {
        
//		var url = 'wss://jitsi.hubble.kr/xmpp-websocket';
		var url = 'ws://127.0.0.1:28082/server/websocket';
		
		
		// WebSocket 오브젝트 생성(자동으로 접속 시작한다 - OnOpen 함수 호출)
		var webSocket = new WebSocket(url);
		
		// WebSocket 서버와 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			console.log('server connected : ' + message);
		}
		
		// WebSocket 서버와 접속이 끊기면 호출되는 함수
		webSocket.onclose = function(message) {
			console.log('server disconnected : ' + JSON.stringify(message));
		}
		
		// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
		webSocket.onerror = function(message) {
			console.log('error : ' + JSON.stringify(message));
		}
		
		// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
		webSocket.onmessage = function(message) {
			if (typeof onMessageCallback === "function") {
				var msg = WebSocketUtil.receiveMessage(message);
				callback(msg);
			}
		}

/*
		//let socket = new SockJS('/server/gs-guide-websocket');
		let socket = new SockJS('/server/websocket');

		let token = WebUtil.getStorageData('jwtToken', false);			
		let header = {
			"Authorization" : "Bearer " + token.jwtToken
		}
		console.log(header);
		$this.socketClient = Stomp.over(socket);
		$this.socketClient.connect(header, function(frame) {
			setConnected(true);
			console.log('connected : ' + frame);
			$this.subscribe('/topic/greetings', function(greeting) {
				console.log(greeting);
			});
		});
*/
		return webSocket;

    },
	
    /**
     * sendMessage
     * json : message
     */
    sendMessage: function(webSocket, msg) {
        
		webSocket.send(msg);
		//$this.socketClient.send('/app/hello', {}, '이것은 무엇인고');

    },

    /**
     * receiveMessage
     * json : message
     */
    receiveMessage: function(message) {
        console.log('Recieve from Server : ' + message);
    },

    /**
     * closeWebSocket
     * obj : 오브젝트
     */
    closeWebSocket: function(webSocket) {
        webSocket.close();
    }
};
