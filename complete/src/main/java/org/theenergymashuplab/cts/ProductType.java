/*
 * Copyright 2019-2025 The Energy Mashup Lab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.theenergymashuplab.cts;

/**
 * HOW  -- the resource is packaged for trading
 *
 * @author crossover
 * @version 1.0
 * @created 18-Feb-2025 11:30:38 AM
 */
public class ProductType extends ResourceType {

    public DurationType duration;
    public int quantityScale;
    public WarrantIdType warrants;

    public ProductType() {

    }

    public ProductType(DurationType duration, int quantityScale, WarrantIdType warrants) {
        this.duration = duration;
        this.quantityScale = quantityScale;
        this.warrants = warrants;
    }

    public void setWarrants(WarrantIdType warrants) {
        this.warrants = warrants;
    }

    public void setQuantityScale(int quantityScale) {
        this.quantityScale = quantityScale;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }

    public WarrantIdType getWarrants() {
        return warrants;
    }

    public int getQuantityScale() {
        return quantityScale;
    }

    public DurationType getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "duration=" + duration +
                ", quantityScale=" + quantityScale +
                ", warrants=" + warrants +
                '}';
    }

    //	public void finalize() throws Throwable {
//		super.finalize();
//	}

}