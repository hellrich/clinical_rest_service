package de.julielab.restservice.annotation.pipelines;

import java.util.List;

import de.julielab.restservice.annotation.Entity;

public interface IPipeline {
	public List<Entity> process(String text, String fromEncoding)
			throws Exception;
}