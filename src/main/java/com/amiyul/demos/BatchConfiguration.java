/*
 * Add Copyright
 */
package com.amiyul.demos;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Bean
	public JobLauncher getJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
	
	@Bean
	public Job getJob(Step step) {
		return jobBuilderFactory.get("Locations Job").start(step).build();
	}
	
	@Bean
	public ItemReader getReader() throws Exception {
		FlatFileItemReader reader = new FlatFileItemReader();
		reader.setResource(new ClassPathResource("locations.csv"));
		DefaultLineMapper lineMapper = new DefaultLineMapper();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
		lineMapper.setFieldSetMapper(new LocationFieldSetMapper());
		reader.setLineMapper(lineMapper);
		reader.afterPropertiesSet();
		return reader;
	}
	
	@Bean
	public ItemProcessor getProcessor() throws Exception {
		LocationProcessor processor = new LocationProcessor();
		processor.afterPropertiesSet();
		return processor;
	}
	
	@Bean
	public ItemWriter getWriter() throws Exception {
		ConsoleLocationWriter writer = new ConsoleLocationWriter();
		writer.afterPropertiesSet();
		return writer;
	}
	
	@Bean
	public Step getStep(ItemReader reader, ItemProcessor processor, ItemWriter writer) {
		return steps.get("Step: RPW").chunk(50).reader(reader).processor(processor).writer(writer).build();
	}
	
}
