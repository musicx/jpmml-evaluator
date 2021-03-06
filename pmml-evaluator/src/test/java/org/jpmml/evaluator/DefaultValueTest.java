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

import java.util.Map;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.Target;
import org.dmg.pmml.Targets;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultValueTest extends ModelEvaluatorTest {

	@Test
	public void evaluate() throws Exception {
		ModelEvaluator<?> evaluator = createModelEvaluator();

		ModelEvaluationContext context = evaluator.createContext(null);

		Map<FieldName, ? extends Number> predictions = TargetUtil.evaluateRegressionDefault(context);

		assertEquals(1, predictions.size());

		Number amount = predictions.get(evaluator.getTargetField());

		assertEquals(432.21d, amount);
	}

	@Test
	public void evaluateEmptyTarget() throws Exception {
		ModelEvaluator<?> evaluator = createModelEvaluator();

		Targets targets = evaluator.getTargets();
		for(Target target : targets){
			(target.getTargetValues()).clear();
		}

		ModelEvaluationContext context = evaluator.createContext(null);

		Map<FieldName, ? extends Number> predictions = TargetUtil.evaluateRegressionDefault(context);

		assertEquals(1, predictions.size());

		Number amount = predictions.get(evaluator.getTargetField());

		assertEquals(null, amount);
	}
}