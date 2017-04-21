/**
 * Created by WQ on 2014/9/3.
*/

(function () {
	var layout = document.querySelector( "#layout" );
	var pages = document.querySelectorAll( ".page" );
	var Height = layout.offsetHeight;
	var Width = layout.offsetWidth;

	//var audio = document.querySelector( "audio" );
	//var musicLogo = document.querySelector( ".music-logo" );
	//var isStart = false;

	//window.spHeight = Height;
	//Z.onTouchStart( pages[0], function () {
//		if ( isStart == false ) {
//			musicLogo.classList.add( "playing" );
//			audio.src = "images/bg.mp3";
//			audio.play();
//		}
//		isStart = true;
//	} );
	//Z.onTap( musicLogo, function () {
//		musicLogo.classList.contains( "playing" ) ? audio.pause() : audio.play();
//		musicLogo.classList.toggle( "playing" );
//	} );


	//var page5MapWidth = Height * 0.3194 * 511 / 384;
//	Z.insertCSSRules( {
//		".page5-arrow-point" : {
//			left : (Width - page5MapWidth) / 2 + page5MapWidth * 0.3189 + "px"
//		},
//		".page2-bh" : {
//			left : (Width - Height * 0.45) / 2 + "px"
//		},
//		".page3-circle-point.a" : {
//			top : (Height * 0.4375 - 15) + "px",
//			left : (Width / 2 - 15) + "px"
//		},
//		".page3-circle-point.b" : {
//			top : (Height * 0.5952 - 15) + "px",
//			left : (Width / 2 + Height * 0.09 - 15) + "px"
//		},
//		".page3-circle-point.c" : {
//			top : (Height * 0.7529 - 30) + "px",
//			left : (Width / 2 - Height * 0.09 - 15) + "px"
//		},
//		".page3-img2" : {
//			bottom : (Height * 0.4048 + 15) + "px",
//			left : (Width / 2 + Height * 0.09 ) + "px",
//			"-webkit-transform" : "translate3d(-50%,0,0)"
//		},
//		".page3-img3" : {
//			bottom : (Height * 0.2471 + 30) + "px",
//			left : (Width / 2 - Height * 0.09 ) + "px",
//			"-webkit-transform" : "translate3d(-50%,0,0)"
//		}
//	} );

	// 第0页
	pages[0].onCut = function () {
		setTimeout( function () {
			pages[0].classList.add( "animate" );
		}, 0 );
	};
	pages[0].onRemove = function () {
		this.classList.remove( "animate" );
	};


	//var commentWall = sp.commentWall( document.querySelector( ".comment-wall" ), data.userPicData );
	//sp.onPointerDown( commentWall.querySelector( ".sphere-parent" ), function ( event ) {
	//	event.preventDefault();
	//	event.stopPropagation();
	//} );

	// 第1页
	pages[1].onCut = function () {
		//commentWall.runAnimate();
		pages[1].classList.add( "animate" );
	};
	pages[1].onRemove = function () {
		//commentWall.removeTips();
		//commentWall.stopAnimate();
		this.classList.remove( "animate" );
	};

	var Page2Aniamte = null;
	var lines = [];

	function setPage2( page ) {
		var Height = page.offsetHeight;
		var offsetH = page.offsetHeight * 0.2232;
		//var border = page.querySelector( ".page2-border" );
		var css = Z.css;
		var y = 0;
		Z.loop( 33, function ( i ) {
			var img = new Image();
			if ( i == 11 ) {
				return;
			}
			if ( i == 0 ) {
				//img.src = "images/page2-title1.png";
				img.wheight = Height * 0.0952;
				img.classList.add( "page2-title" );
			}
			else if ( i == 13 ) {
				//img.src = "images/page2-title2.png";
				img.wheight = Height * 0.0952;
				img.classList.add( "page2-title" );
			}
			else {
				// img.src = "img/page2-" + (i > 13 ? i - 1 : i) + ".png";
				img.wheight = Height * 0.0773;
				img.classList.add( "page2-line" );
			}
			css( img, {
				"-webkit-transform" : "translate3d(-50%," + (1.6 * offsetH + y) + "px,0) scale(" + (y / 500 + 1) + ")"
			} );
			img.wy = offsetH + y;
			y = y + img.wheight;
			lines.push( img );
			//border.appendChild( img );
		} );
		Page2Aniamte = Z.requestAnimate( function () {
			Z.loopArray( lines, function ( line, i ) {
				if ( line.wy > 0 ) {
					line.wy = line.wy - 0.7;
					css( line, {
						"-webkit-transform" : "translate3d(-50%," + line.wy + "px,0) scale(" + (line.wy / 500 + 1 - 1.6 * offsetH / 500) + ")"
					} );
				}
				else {
					css( line, {
						"opacity" : 0
					} );
				}
			} );
		} );
		Page2Aniamte.stop();
		Z.onTouchStart( page, function () {
			Page2Aniamte.stop();
		} );

		Z.onTouchEnd( page, function () {
			Page2Aniamte.start();
		} );
	}

	setPage2( pages[2] );
	// 第2页
	pages[2].onCut = function () {
		this.classList.add( "animate" );
		Page2Aniamte.start();
	};
	pages[2].onRemove = function () {

		var y = 0;
		var offsetH = pages[2].offsetHeight * 0.2232;

		Z.loopArray( lines, function ( line ) {
			Z.css( line, {
				"-webkit-transform" : "translate3d(-50%," + (1.6 * offsetH + y) + "px,0) scale(" + (y / 500 + 1) + ")",
				"opacity" : "1"
			} );

		line.wy = offsetH + y;
			y = y + line.wheight;
		} );
		this.classList.remove( "animate" );
		setTimeout( function () {
			Page2Aniamte.stop();
		}, 0 );
	};


	function setPage4( page ) {
		var points = page.querySelectorAll( ".page3-circle-point" );
		var imgs = [];
		Z.loopArray( points, function ( point, i ) {
			Z.onTap( point, function () {
				if ( !imgs[i] ) {
					// 生成
					var img = new Image();
					img.src = "img/page3-img" + (i + 1) + ".png";
					img.classList.add( "page3-img" + (i + 1) );
					Z.onTap( img, function () {
						page.removeChild( img );
					} );
					imgs[i] = img;
				}
				page.appendChild( imgs[i] );
			} );
		} );
	}

	setPage4( pages[4] );
	// 第3页
	pages[3].onCut = function () {
		this.classList.add( "animate" );
	};
	pages[3].onRemove = function () {
		this.classList.remove( "animate" );
	};

	//第4页
	pages[4].onCut = function () {
		this.classList.add( "animate" );
	};
	pages[4].onRemove = function () {
		this.classList.remove( "animate" );
	};


	//第5页
	pages[5].onCut = function () {
		this.classList.add( "animate" );
	};
	pages[5].onRemove = function () {
		this.classList.remove( "animate" );
	};

	//var mapPage = null;
//
//	function openMapPage() {
//		if ( mapPage == null ) {
//			// 生成地图页面
//			mapPage = template.make( "page-map" );
//			document.body.appendChild( mapPage );
//			var position = new AMap.LngLat( 116.308093, 39.944489 );
//			new AMap.Marker( {
//				map : new AMap.Map( mapPage.querySelector( "#container" ), {
//					view : new AMap.View2D( {
//						center : position,
//						zoom : 15,
//						rotation : 0
//					} ),
//					lang : "zh_cn"//设置语言类型，中文简体
//				} ),
//				position : position,
//				offset : new AMap.Pixel( -10, -34 ),
//				icon : "images/0.png"
//			} );
//			Z.onTap( mapPage.querySelector( ".c-title-bar" ), function () {
//				document.body.removeChild( mapPage );
//			} );
//		}
//		else {
//			document.body.appendChild( mapPage );
//		}
//	}

	//Z.onTap( pages[6].querySelector( ".page5-map" ), openMapPage );
	//第6页
	pages[6].onCut = function () {
		this.classList.add( "animate" );
	};
	pages[6].onRemove = function () {
		this.classList.remove( "animate" );
	};

	//第7页
	// pages[7].onCut = function () {
	// 	this.classList.add( "animate" );
	// };
	// pages[7].onRemove = function () {
	// 	this.classList.remove( "animate" );
	// };

	//Z.onTap( document.querySelector( ".page5-btn" ), function () {
	//	location.href = "/";
	//} );


	// document.querySelector( ".page6-href" ).href = Z.ua.ios ? Z.ua.MicroMessenger ? "/" : "/" : "/";
	// Z.onTap( document.querySelector( ".page6-btn" ), function () {
	// 	if ( Z.ua.ios ) {
	// 		if ( Z.ua.MicroMessenger ) {
	// 			location.href = "/";
	// 		}
	// 		else {
	// 			location.href = "/";
	// 		}
	// 	}
	// 	else {
	// 		location.href = "/";
	// 	}
	// } );


	setTimeout( function () {
		document.body.removeChild( document.querySelector( ".page-loading" ) );
		lib.ScreenSystem( document.getElementById( "layout" ) );
	}, 3000 );

})();