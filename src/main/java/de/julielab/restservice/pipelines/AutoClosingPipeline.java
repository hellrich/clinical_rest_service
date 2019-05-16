package de.julielab.restservice.pipelines;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import de.julielab.restservice.annotation.Entity;

public class AutoClosingPipeline implements AutoCloseable, IPipeline {

	private final IPipeline pipeline;
	private final ArrayBlockingQueue<IPipeline> pipelines;

	public AutoClosingPipeline(ArrayBlockingQueue<IPipeline> pipelines)
			throws InterruptedException {
		this.pipelines = pipelines;
		this.pipeline = pipelines.take();
	}

	@Override
	public void close() throws Exception {
		pipelines.put(pipeline);
	}

	@Override
	public List<Entity> process(String text, String fromEncoding)
			throws Exception {
		return pipeline.process(text, fromEncoding);
	}

}