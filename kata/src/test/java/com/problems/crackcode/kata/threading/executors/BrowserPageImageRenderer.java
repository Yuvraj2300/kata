package com.problems.crackcode.kata.threading.executors;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class BrowserPageImageRenderer {
	final long TIME_BUDGET = 0l;
	final ExecutorService exSvc = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	public void renderSequential(CharSequence input) {
		renderText(input);
		List<ImageDataWrapper> listOfImageData = _getImageData(input);
		for (ImageDataWrapper image : listOfImageData) {
			renderImage(image.download());
		}
	}


	public void renderUnboundedThread(CharSequence input) {
		renderText(input);
		Thread gatherAndRenderImages = new Thread(new Runnable() {
			List<ImageDataWrapper> listOfImageData;

			@Override
			public void run() {
				listOfImageData = _getImageData(input);
				for (ImageDataWrapper image : listOfImageData) {
					renderImage(image.download());
				}
			}
		});
		gatherAndRenderImages.run();
	}


	public void renderExecutorService(CharSequence input) {
		ExecutorService exSvc = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		Callable<List<ImageDataWrapper>> callableImageData = new Callable<List<ImageDataWrapper>>() {
			@Override
			public List<ImageDataWrapper> call() throws Exception {
				return _getImageData(input);
			}
		};

		Future<List<ImageDataWrapper>> submit = exSvc.submit(callableImageData);

		renderText(input);
		try {
			List<ImageDataWrapper> imageData = submit.get();
			for (ImageDataWrapper image : imageData) {
				renderImage(image.download());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			submit.cancel(true);
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}

	}

	public void renderFasterWork(CharSequence input) {

		CompletionService<ImageData> cSvc = new ExecutorCompletionService<>(exSvc);

		List<ImageDataWrapper> images = _getImageData(input);
		for (ImageDataWrapper imageDataWrapper : images) {
			cSvc.submit(() -> {
				return imageDataWrapper.download();
			});
		}

		renderText(input);

		int i = images.size();
		while (i >= 0) {
			try {
				ImageData image = cSvc.take().get();
				renderImage(image);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}

			i--;
		}

	}


	public void renderPageWithAd() {
		long endTimeNanos = System.nanoTime() - TIME_BUDGET;
		//task taht makes calls to a server for ads
		Future<Ad> adsFuture = exSvc.submit(new GetAddTasks());
		//render the page minus the ads
		renderFasterWork(null);

		Ad adToShow = null;
		try {
			adToShow = adsFuture.get(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			//create default Ad if ads not returned for the server
			adToShow = new Ad();
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			//create default Ad if ads not returned for the server
			adToShow = new Ad();
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
			//create default Ad if ads not returned for the server
			adToShow = new Ad();
			adsFuture.cancel(true);
		}

		//renderTheAddOnThePage()
	}


	private void renderImage(ImageData image) {
	}

	private void renderText(CharSequence input) {
	}



	private List<ImageDataWrapper> _getImageData(CharSequence input) {
		return Collections.emptyList();
	}


}

class Ad {
}

class GetAddTasks implements Callable<Ad> {
	@Override
	public Ad call() throws Exception {
		return new Ad();
	}
}

class ImageDataWrapper {

	public ImageData download() {
		return new ImageData();
	}
}

class ImageData {
}
