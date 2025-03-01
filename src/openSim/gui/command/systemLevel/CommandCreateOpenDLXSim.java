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
package openSim.gui.command.systemLevel;

import java.io.File;

import javax.swing.JOptionPane;

import alternateSimulator.Simulator;
import openSim.gui.MainFrame;
import openSim.gui.GUI_CONST.OpenDLXSimState;
import openSim.gui.command.Command;

public class CommandCreateOpenDLXSim implements Command
{

    private MainFrame mf;//in
    private Simulator openDLXSim; // out

    public CommandCreateOpenDLXSim(MainFrame mf)
    {
        this.mf = mf;
    }

    @Override
    public void execute()
    {
        try
        {
            //create new openDLX simulator
            openDLXSim = new Simulator();
            //state executing means openDLX is loaded
            mf.setOpenDLXSimState(OpenDLXSimState.EXECUTING);
            // assign new openDLX to MainFrame
            mf.setSimulator(openDLXSim);
            //assign new configFile to OpenDLXSim
        }
        catch (Exception e)
        {
            openDLXSim = null;
            mf.setSimulator(openDLXSim);
            System.err.println(e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(mf, "Using configFile to create openDLX failed");
        }

    }

}
