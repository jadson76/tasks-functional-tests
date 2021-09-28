package com.jadson.tasks.funcional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class TaskTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://172.22.224.1:4444/wd/hub"),cap);
		driver.navigate().to("http://172.22.224.1:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();		
		try {			
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Segundo Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!",mensagem);
		} finally {
			driver.quit();
		} 		
		
	}
	
	@Test
	public void naoDeveSalvarTarefasemDescricao() throws MalformedURLException {
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
	public void naoDeveSalvarTarefasSemDatao() throws MalformedURLException {
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
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
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
