<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Videos</title>
</head>
<body>
	<p>
	<div id="player"
		style="position: absolute; left: 20%; top: 20%; height: 60%; width: 60%"></div>

	<script>
		
		var tag = document.createElement('script');

		tag.src = "https://www.youtube.com/iframe_api";
		var firstScriptTag = document.getElementsByTagName('script')[0];
		firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

		var player;
		function onYouTubeIframeAPIReady() {
			player = new YT.Player('player', {
				height : '390',
				width : '640',
				videoId : 'OMOGaugKpzs',
				events : {
					'onReady' : onPlayerReady,
					'onStateChange' : onPlayerStateChange
				}
			});
		}

		function onPlayerReady(event) {
			event.target.playVideo();
		}

		function stopVideo() {
			player.stopVideo();
		}
		
		function playerLoadVideo(videoId) {
			player.loadVideoById(videoId);
		}
		
		function onPlayerStateChange(event) {
			alert("New state :" + event.data);
			var progress = (player.getVideoLoadedFraction() * 100);
			alert("Video Loaded Percentage :" + progress);
		}
	</script>

	<div align="center">
		<a href="javascript:playerLoadVideo('OMOGaugKpzs');">1</a> <a
			href="javascript:playerLoadVideo('dOibtqWo6z4');">2</a> <a
			href="javascript:playerLoadVideo('9Q7Vr3yQYWQ');">3</a> <a
			href="javascript:playerLoadVideo('RKrNdxiBW3Y');">4</a> <a
			href="javascript:playerLoadVideo('Pep6nREBpS8');">5</a>
	</div>
</body>
</html>