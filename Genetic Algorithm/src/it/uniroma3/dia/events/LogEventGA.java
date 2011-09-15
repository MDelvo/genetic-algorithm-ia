package it.uniroma3.dia.events;

import java.util.EventListener;

public interface LogEventGA extends EventListener{
	  public void populationEvolutionFinished(EventFinishGA evt);
}
