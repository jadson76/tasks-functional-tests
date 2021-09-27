package com.jadson.tasks.funcional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TaskTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();		
		try {			
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Segundo Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!",mensagem);
		} finally {
			driver.quit();
		} 		
		
	}
	
	@Test
	public void naoDeveSalvarTarefasemDescricao() {
		WebDriver driver = acessarAplicacao();		
		try {			
			driver.findElement(By.id("addTodo")).click();			
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description",mensagem);
		} finally {
			driver.quit();
		} 		
		
	}
	
	@Test
	public void naoDeveSalvarTarefasSemDatao() {
		WebDriver driver = acessarAplicacao();		
		try {			
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Segundo Teste via Selenium");			
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date",mensagem);
		} finally {
			driver.quit();
		} 		
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();		
		try {			
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("terceiro Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2019");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past",mensagem);
		} finally {
			driver.quit();
		} 		
		
	}
	

}
