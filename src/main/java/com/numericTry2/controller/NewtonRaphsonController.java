package com.numericTry2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.numericTr2.calculator.methods.BisectionMethod;
import com.numericTr2.calculator.methods.FalseRuleMethod;
import com.numericTr2.calculator.methods.GaussianQuadrature;
import com.numericTr2.calculator.methods.NewtonRaphsonMethod;
import com.numericTr2.calculator.methods.SecantMethod;


//import com.MetodosNumericos.calculator.methods.NewtonRaphsonMethod;
@Controller
public class NewtonRaphsonController {
	
    @GetMapping("/")
    public String showIndex() {
        return "index";  // Esto apuntará a index.html
    }
	
	@GetMapping("/newton")
    public String showNewtonForm() {
        return "newton";
    }
	
	@GetMapping("/bisection")
    public String showBisectionForm() {
        return "bisection";
    }
	
	@GetMapping("/falserule")
	public String showFalseRuleForm() {
		return "falserule";
	}
	
	@GetMapping("/secant")
	public String showSecantForm() {
		return "secant";
	}
	
	@GetMapping("/quadratureGaussian")
	public String showGaussianForm() {
		return "quadratureGaussian";
	}
	

	@PostMapping("/newton")
	public String calculateNewton(
	            @RequestParam String function,
	            @RequestParam double startPoint,
	            Model model) {
	        
	        try {
	        	int intStartPoint = (int) startPoint;
	        	NewtonRaphsonMethod method = new NewtonRaphsonMethod();
		        double result;
	            result = method.newthonRpMethod(function, startPoint);
	            
	            
	            
	            model.addAttribute("result", result);
		        model.addAttribute("function", function);
		        model.addAttribute("startPoint", intStartPoint);	        
	        } catch (Exception e) {
	            model.addAttribute("error", "Hubo un error al calcular: " + e.getMessage()); 
	        }

	        return "newton";
	    }
	
	@PostMapping("/bisection")
	public String calculateBisection(@RequestParam String function,@RequestParam double startPoint,@RequestParam double endPoint,Model model) {
			try {	
				int intStartPoint = (int) startPoint;
				int intEndPoint = (int) endPoint;
				
				double result;
				
				BisectionMethod method = new BisectionMethod();
				
				result = method.bisectionMethods(function, startPoint, endPoint);
				
				if (startPoint > endPoint|| startPoint == endPoint ) {
					model.addAttribute("error", "No cumple el teorema de bisección");
				} else {
					model.addAttribute("result", result);
				}
				
				model.addAttribute("function", function);
				model.addAttribute("startPoint", intStartPoint);
				model.addAttribute("endPoint", intEndPoint);
			} catch (Exception e) {
				model.addAttribute("error", "Hubo un error al calcular: " + e.getMessage());
    
			}
			return "bisection";
		}
	
	@PostMapping("/falserule")
	public String calculateFalseRule(@RequestParam String function,@RequestParam double startPoint,@RequestParam double endPoint,Model model) {
		try {
			int intStartPoint = (int) startPoint;
			int intEndPoint = (int) endPoint;

			double result;

			FalseRuleMethod method = new FalseRuleMethod();

			result = method.falseRuleMethod(function, startPoint, endPoint);

			if (startPoint > endPoint || startPoint == endPoint) {
				model.addAttribute("error", "No cumple el teorema de convergencia");
			} else {
				model.addAttribute("result", result);
			}

			model.addAttribute("function", function);
			model.addAttribute("startPoint", intStartPoint);
			model.addAttribute("endPoint", intEndPoint);
		} catch (Exception e) {
			model.addAttribute("error", "Hubo un error al calcular: " + e.getMessage());

		}
		return "falserule";
	}
	
	@PostMapping("/secant")
	public String calculateSecant(
			@RequestParam String function,
			@RequestParam double startPoint,
			@RequestParam double endPoint,
			Model model){
		
		
	try {
			int intStartPoint = (int) startPoint;
			int intEndPoint = (int) endPoint;
			
			double result;
			SecantMethod method = new SecantMethod();
			
			result = method.secantMethod(function, startPoint, endPoint);
			if (startPoint > endPoint || startPoint == endPoint) {
				model.addAttribute("error", "No cumple el teorema de convergencia");
			} else {
				model.addAttribute("result", result);
			}
			
			model.addAttribute("function", function);
			model.addAttribute("startPoint",intStartPoint);
			model.addAttribute("endPoint", intEndPoint);
			
		
	} catch (Exception e) {
		model.addAttribute("error", "Hubo un error al calcular: " + e.getMessage());

	}
		return "secant";
	}
	
	@PostMapping("/quadratureGaussian")
    public String calculateGaussian(
			@RequestParam String function, @RequestParam double a, @RequestParam double b, Model model) {
			System.out.println("function: "+function);
		try {
			double result;
			
			GaussianQuadrature method = new GaussianQuadrature();
			result = method.fourthPounds(function, a, b);

			model.addAttribute("result", result);
			model.addAttribute("function", function);
			model.addAttribute("a", a);
			model.addAttribute("b", b);
		} catch (Exception e) {
			model.addAttribute("error", "Hubo un error al calcular: " + e.getMessage());
		}

		return "quadratureGaussian";
	}

	
	  
	  @GetMapping("/xd")
	  	@ResponseBody
	  	public String index() {
			return "Hello from Newton Raphson";
		}
	
}