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
package openSim.gui.internalframes;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

@SuppressWarnings("serial")
public abstract class OpenDLXSimInternalFrame extends JInternalFrame implements Updateable, Cleanable
{


    public OpenDLXSimInternalFrame(String name, boolean maximizable)
    {
        super(name,
                true, //resizable
                true, //closable
                maximizable, //maximizable
                true //minimizable
                );
    }

    protected void initialize()
    {
        addInternalFrameListener(new InternalFrameAdapter()
        {
            @Override
            public void internalFrameClosing(InternalFrameEvent e)
            {
                setDefaultCloseOperation(HIDE_ON_CLOSE);
            }

        });
    }

}
