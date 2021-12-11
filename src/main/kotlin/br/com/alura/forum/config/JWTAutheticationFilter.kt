package br.com.alura.forum.config

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAutheticationFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        if (jwtUtil.isValid(jwt)) {
            val authetication = jwtUtil.getAuthetication(jwt)
            SecurityContextHolder.getContext().authentication = authetication
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String): String {
        return token.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, jwt.length)
        }
    }

}
