package org.ugate.service.web;

import static org.ugate.service.web.WebServer.RP_REMOTE_NODE_ADDY;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.servlet.DefaultServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ugate.service.ServiceProvider;
import org.ugate.service.entity.RemoteNodeType;
import org.ugate.service.entity.jpa.RemoteNode;

public class UGateAjaxUpdaterServlet extends DefaultServlet {

	private static final long serialVersionUID = 3081647720588957725L;
	private static final Logger log = LoggerFactory.getLogger(UGateAjaxUpdaterServlet.class);

	/**
	 * Validates the request
	 * 
	 * @param request
	 *            the {@link HttpServletRequest}
	 * @param response
	 *            the {@link HttpServletResponse}
	 * @return true when validation is successful
	 * @throws ServletException
	 *             the {@link ServletException}
	 * @throws IOException
	 *             the {@link IOException}
	 */
	protected boolean validate(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		return true;
	}

	/**
	 * Does all the HTTP operations
	 * 
	 * @param request
	 *            the {@link HttpServletRequest}
	 * @param response
	 *            the {@link HttpServletResponse}
	 * @throws ServletException
	 *             the {@link ServletException}
	 * @throws IOException
	 *             the {@link IOException}
	 */
	protected void doAll(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type: application/json", Boolean.TRUE.toString());
//		try {
//			getServletContext().getNamedDispatcher("default").forward(request, response);
//		} catch (final Throwable t) {
//			log.error("Error: ", t);
//			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doHead(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			if (validate(request, response)) {
				final String addy = request.getParameter(RP_REMOTE_NODE_ADDY);
				if (addy != null && !addy.isEmpty()) {
					final RemoteNode rn = ServiceProvider.IMPL.getRemoteNodeService().findByAddress(addy);
					if (rn == null) {
						log.warn(String.format("Unable to find %1$s with %2$s = %3$s Aborting PUT operation", 
								RemoteNode.class.getSimpleName(), RemoteNodeType.WIRELESS_ADDRESS, addy));
						return;
					}
					boolean hasParams = false;
					String p;
					Object v;
					for (final RemoteNodeType rnt : RemoteNodeType.values()) {
						p = request.getParameter(rnt.name());
						if (p == null || p.isEmpty()) {
							continue;
						}
						v = rnt.getValue(rn);
						if (v != null && v.toString().equals(p)) {
							continue;
						}
						if (log.isInfoEnabled()) {
							log.info(String.format("Setting %1$s request parameter %2$s to %3$s", 
									RemoteNode.class.getSimpleName(), rnt.getKey(), p));
						}
						rnt.setValue(rn, p);
						hasParams = true;
					}
					if (hasParams) {
						ServiceProvider.IMPL.getRemoteNodeService().merge(rn);
					}
				} else {
					log.warn(String.format("Request %1$s must contain %2$s in order to perform PUT", 
							request, RP_REMOTE_NODE_ADDY));
				}
			}
		} catch (final Throwable t) {
			log.error("Error: ", t);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doOptions(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doTrace(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
}