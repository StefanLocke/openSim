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

import java.awt.EventQueue;

import alternateSimulator.Simulator;
import openSim.exception.PipelineException;
import openSim.gui.GUI_CONST;
import openSim.gui.MainFrame;

public class ThreadCommandRunSlowly implements Runnable
{

    private MainFrame mf;

    public ThreadCommandRunSlowly(MainFrame mf)
    {
        this.mf = mf;
    }

    @Override
    public void run()
    {
        Simulator simulator = mf.getSimulator();
        //start running
        mf.setOpenDLXSimState(GUI_CONST.OpenDLXSimState.RUNNING);
        //check if running was paused/quit or if openDLXSim has finished
        while (!simulator.isFinished() && mf.isRunning())
        {
            while (mf.isPause())
            {
                try
                {
                    Thread.sleep(100);
                }
                catch (Exception e) {}
            }

            // do a cycle within openDLX
            try
            {
                simulator.step();
            }
            catch (PipelineException e)
            {
                mf.getPipelineExceptionHandler().handlePipelineExceptions(e);
            }

            try
            {
                //queue CommandUpdateFrames/execute() to event dispatch thread
                EventQueue.invokeAndWait(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //update frames
                        new CommandUpdateFrames(mf).execute();
                    }

                });
                //wait a certain amount of time, which is defined within MainFrame
                Thread.sleep(mf.getRunSpeed() * 100);
            }
            catch (Exception e)
            {
                System.err.println(e.toString());
                e.printStackTrace();
            }

        }
        // when running stops or openDLX has finished, set state back to executing, as executing means a openDLX is loaded but not running through
        mf.setOpenDLXSimState(GUI_CONST.OpenDLXSimState.EXECUTING);
        // if the current openDLX has finished, dont allow any gui updates any more
        if (simulator.isFinished())
        {
            mf.setUpdateAllowed(false);
            new CommandSimulatorFinishedInfo().execute();
        }
    }

}
