/*
 * Copyright (c) 2013 Villu Ruusmann
 *
 * This file is part of JPMML-Evaluator
 *
 * JPMML-Evaluator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-Evaluator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-Evaluator.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpmml.evaluator;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.Target;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TargetUtilTest {

	@Test
	public void processValue(){
		Target target = new Target(new FieldName("amount"))
			.withRescaleFactor(3.14d)
			.withRescaleConstant(10d);

		assertEquals(35.12d, (Double)TargetUtil.processValue(target, 8d), 1.e-8);

		target = target.withCastInteger(Target.CastInteger.ROUND)
			.withMin(-10d)
			.withMax(10.5d);

		assertEquals(35, TargetUtil.processValue(target, 8d));
		assertEquals(43, TargetUtil.processValue(target, 12.97d));
	}
}