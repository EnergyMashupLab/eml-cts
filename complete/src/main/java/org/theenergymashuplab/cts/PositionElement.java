/*
 * Copyright 2019-2020 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

/* for position manager interactions 
 * This will be a list, each within the bounding interval in EiRequestPostion
 */

/*
 * Old position manager design
 * TODO rationalize or delete or integrate
 */
public class PositionElement {
	Interval interval;
	long position;
	
	PositionElement(Interval interval, long position)	{
		this.interval = interval;
		this.position = position;
	}
}
