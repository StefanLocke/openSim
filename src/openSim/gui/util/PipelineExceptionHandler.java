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
package openSim.gui.util;

import javax.swing.JOptionPane;

import alternateSimulator.Simulator;
import openSim.exception.DecodeStageException;
import openSim.exception.PipelineException;
import openSim.exception.UnknownInstructionException;
import openSim.gui.MainFrame;
import openSim.gui.GUI_CONST.OpenDLXSimState;

public class PipelineExceptionHandler {
	
	private MainFrame mf;
	private Simulator sim = null;



	public PipelineExceptionHandler(MainFrame main)
	{
		mf = main;
	}
	
	 
	public void handlePipelineExceptions(PipelineException e) 
	{
		Class<? extends PipelineException> type = e.getClass();
		if (type == UnknownInstructionException.class) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mf, e.getMessage(), "Unsupported Instruction Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (type == DecodeStageException.class) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mf, e.getMessage(), "Decode Stage Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			e.printStackTrace();
			JOptionPane.showMessageDialog(mf, e.getMessage(), "General Pipeline Error",
					JOptionPane.ERROR_MESSAGE);
		}
		sim.stopSimulation(true);
		// TODO: better set to an error state?
		mf.setOpenDLXSimState(OpenDLXSimState.IDLE);
	}



	public void setSimulator(Simulator simulator) 
	{
		sim = simulator;
	}
}
