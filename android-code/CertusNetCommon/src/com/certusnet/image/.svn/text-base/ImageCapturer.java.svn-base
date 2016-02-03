package com.certusnet.image;


public class ImageCapturer {
	
	public ImageCapturer(){
	}
	
	public IImageCapturer newInstance(String url){
		if(url.startsWith("http://")){
			return new RemoteImage(url);
		}else{
			return new LocalImage(url);
		}
	}

}
