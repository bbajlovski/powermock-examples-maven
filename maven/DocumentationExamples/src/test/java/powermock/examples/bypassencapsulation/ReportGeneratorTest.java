/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package powermock.examples.bypassencapsulation;

import org.junit.Test;
import org.powermock.reflect.Whitebox;
import powermock.examples.bypassencapsulation.nontest.Report;
import powermock.examples.bypassencapsulation.nontest.ReportTemplateService;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for the {@link ReportGenerator} class. Demonstrates the ability for
 * PowerMock to easily set internal state of a class.
 */
public class ReportGeneratorTest {

	@Test
	public void testGenerateReport() throws Exception {
		ReportGenerator tested = new ReportGenerator();
		ReportTemplateService reportTemplateServiceMock = createMock(ReportTemplateService.class);

		Whitebox.setInternalState(tested, "reportTemplateService", reportTemplateServiceMock);

		final String reportId = "id";
		expect(reportTemplateServiceMock.getTemplateId(reportId)).andReturn("templateId");

		replay(reportTemplateServiceMock);

		Report actualReport = tested.generateReport(reportId);

		verify(reportTemplateServiceMock);

		assertEquals(new Report("name"), actualReport);
	}

	@Test
	public void testGenerateReport_usingFieldTypeAppraoch() throws Exception {
		ReportGenerator tested = new ReportGenerator();
		ReportTemplateService reportTemplateServiceMock = createMock(ReportTemplateService.class);

		Whitebox.setInternalState(tested, reportTemplateServiceMock);

		final String reportId = "id";
		expect(reportTemplateServiceMock.getTemplateId(reportId)).andReturn("templateId");

		replay(reportTemplateServiceMock);

		Report actualReport = tested.generateReport(reportId);

		verify(reportTemplateServiceMock);

		assertEquals(new Report("name"), actualReport);
	}
}
