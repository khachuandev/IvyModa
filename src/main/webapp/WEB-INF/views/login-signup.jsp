<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- CSS -->
<link rel="stylesheet" href="${classpath }/frontend/asset/font/fontawesome/fontawesome-free-6.4.0-web/css/all.min.css">
<link rel="stylesheet" href="${classpath }/frontend/asset/css/user.css" />
<title>Login&Signup</title>
</head>
<body>
	<div class="container">
		<div class="forms-container">
			<div class="signin-signup">
				<form method="post" action="${classpath }/login_processing_url"
					class="sign-in-form">
					<h2 class="title">Sign in</h2>
					<c:if test="${not empty param.login_error }">
						<div style="color: #f00" class="alert alert-danger" role="alert">Login attempt
							was not successful, try again!!!</div>
					</c:if>
					<div class="input-field">
						<i class="fas fa-user"></i> <input id="username" name="username" type="text"
							placeholder="Username" />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input id="password" name="password" type="password"
							placeholder="Password" />
					</div>
					<div class="input-field-controls">
						<div class="input-field-check-box">
							<input type="checkbox" id="remember-me" name="remember-me" /> <label
								for="remember-me"> Remember me </label>
						</div>
						<a href="#">Forgot your password?</a>
					</div>
					<input role="button" type="submit" value="Login" class="btn solid" />
					<p class="social-text">Or Sign in with social platforms</p>
					<div class="social-media">
						<a href="#" class="social-icon"> <i class="fab fa-facebook-f"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-twitter"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-google"></i>
						</a> <a href="#" class="social-icon"> <i
							class="fab fa-linkedin-in"></i>
						</a>
					</div>
				</form>
				<form method="post" action="${classpath }/register"
					class="sign-up-form">
					<h2 class="title">Sign up</h2>
					<div class="input-field">
						<i class="fas fa-user"></i> <input id="username" name="username" type="text"
							placeholder="Username" />
					</div>
					<div class="input-field">
						<i class="fas fa-envelope"></i> <input id="email" name="email" type="email"
							placeholder="Email" />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input id="password" name="password" type="password"
							placeholder="Password" />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input id="username" name="username" type="password"
							placeholder="Retype password" />
					</div>
					<div class="input-field">
						<i class="fa-solid fa-phone"></i> <input id="retypepassword" name = "retypepassword" type="text"
							placeholder="Mobile" />
					</div>
					<input type="submit" class="btn" value="Sign up" />
					<p class="social-text">Or Sign up with social platforms</p>
					<div class="social-media">
						<a href="#" class="social-icon"> <i class="fab fa-facebook-f"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-twitter"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-google"></i>
						</a> <a href="#" class="social-icon"> <i
							class="fab fa-linkedin-in"></i>
						</a>
					</div>
				</form>
			</div>
		</div>

		<div class="panels-container">
			<div class="panel left-panel">
				<div class="content">
					<h3>Hello, Friend!</h3>
					<p>Enter your personal details and start journey with us</p>
					<button class="btn transparent" id="sign-up-btn">Sign up</button>
				</div>
				<img src="${classpath }/frontend/asset/image/regester.svg"
					class="image" alt="" />
			</div>
			<div class="panel right-panel">
				<div class="content">
					<h3>Welcome Back!</h3>
					<p>To keep connected with us please login with your personal
						info</p>
					<button class="btn transparent" id="sign-in-btn">Sign in</button>
				</div>
				<img src="${classpath }/frontend/asset/image/logout.svg"
					class="image" alt="" />
			</div>
		</div>
	</div>

	<script>
        const sign_in_btn = document.querySelector("#sign-in-btn");
        const sign_up_btn = document.querySelector("#sign-up-btn");
        const container = document.querySelector(".container");

        sign_up_btn.addEventListener("click", () => {
            container.classList.add("sign-up-mode");
        });

        sign_in_btn.addEventListener("click", () => {
            container.classList.remove("sign-up-mode");
        });
    </script>
</body>
</html>