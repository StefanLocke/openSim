/*******************************************************************************
 * openDLX - A DLX/MIPS processor simulator.
 * Copyright (C) 2013 The openDLX project, University of Augsburg, Germany
 * Project URL: <https://sourceforge.net/projects/opendlx>
 * Development branch: <https://github.com/smetzlaff/openDLX>
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, see <LICENSE>. If not, see
 * <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package openSim.util;

import java.text.DecimalFormat;
import java.util.Properties;

public class Statistics
{
	// Logger
	
	// Singleton
	private static final Statistics instance = new Statistics();
	
	private int cycles;
	private int instructions;
	private int fetches;
	private int memory_reads;
	private int memory_writes;
	private int pipelineFreeze;
	private int cache_hits;
	private int cache_misses;
	

	private Properties config;
	
	private Statistics()
	{
		config = null;
	}
	
	/*
	 * obtain a reference to the object anywhere using:
	 * Statistics stat = Statistics.getInstance();
	 */
	public static Statistics getInstance()
	{
		return instance;
	}
	
	public int getCycles()
	{
		return cycles;
	}
	public int getInstructions()
	{
		return instructions;
	}
	public int getFetches()
	{
		return fetches;
	}
	public int getMemory_reads()
	{
		return memory_reads;
	}
	public int getMemory_writes()
	{
		return memory_writes;
	}
	public int getPipelineFreeze(){
		return this.pipelineFreeze;
	}
	
	public int getCacheMisses() {
		return cache_misses;
	}
	public int getCacheHits() {
		return cache_hits;
	}


	/* 
	 * Implement count functions.
	 */
	public void countCycle()
	{
		cycles++;
	}
	
	public void countInstruction()
	{
		instructions++;
	}
	
	public void countFetch()
	{
		fetches++;
	}
	
	public void countMemRead()
	{
		memory_reads++;
	}

	public void countMemWrite()
	{
		memory_writes++;
	}
	
	public void countPipelineFreeze(){
		pipelineFreeze++;
	}
	
	public void countCacheMisses() {
		cache_misses++;
	}
	
	public void countCacheHits() {
		cache_hits++;
	}


	
	/*
	 * Print the aggregated performance/statistic counter values.
	 */
	public void printStats()
	{

	}
	
	public String toString()
	{
		String stats = "";
		
		DecimalFormat f = new DecimalFormat("###.##");
		stats += "-------- SIMULATION STATISTICS --------\n";
		stats += "Cycles: " + getCycles() + "\n";
		stats += "Executed instructions: " + getInstructions() + "\n";
		stats += "Number of cycles freezed due to pipeline hazards: " + getPipelineFreeze() + "\n";
		stats += "Performed fetches: " + getFetches() + "\n";
		stats += "Cache Hits   : " + getCacheHits() + "\n";
		stats += "Cache Misses : " + getCacheMisses() + "\n";
		stats += "Memory accesses: " + (getMemory_reads() + getMemory_writes()) + " \n\t   reads: " + getMemory_reads() + "\n\t  writes: " + getMemory_writes() + "\n";
		stats += "-------- SIMULATION STATISTICS --------\n";
		
		return stats;
	}

	public void setConfig(Properties config)
	{
		this.config = config;
	}
	


	public void reset() 
	{
		cycles = 0;
		instructions = 0;
		fetches = 0;
		memory_reads = 0;
		memory_writes = 0;
		cache_hits = 0;
		cache_misses = 0;

	}

}
