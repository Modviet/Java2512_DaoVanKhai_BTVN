<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Admin.Users - User Management</title>

  <!-- Fonts + Bootstrap giữ nguyên -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet" />

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />

    <style>
      :root {
        --brand: #e50012;
        --ink: #111827;
        --muted: #6b7280;
        --bg: #f6f7fb;
        --card: #ffffff;
        --ring: rgba(229, 0, 18, .18);
        --bs-border-radius: 0.9rem;
        --bs-border-radius-lg: 1rem;
        --bs-border-radius-sm: 0.7rem;
        --bs-body-color: var(--ink);
        --bs-body-font-family: 'Outfit', system-ui, -apple-system, "Segoe UI", Roboto, Arial, "Noto Sans", "Liberation Sans", sans-serif;
      }

      /* Better default render baseline */
      html, body { height: 100%; }

      body {
        font-family: 'Outfit', system-ui, -apple-system, "Segoe UI", Roboto, Arial, "Noto Sans", "Liberation Sans", sans-serif;
        text-rendering: optimizeLegibility;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        line-height: 1.5;
        background:
          radial-gradient(1200px 500px at 20% -10%, rgba(229, 0, 18, 0.10), transparent 60%),
          radial-gradient(900px 500px at 90% 10%, rgba(17, 24, 39, 0.08), transparent 68%),
          var(--bg);
        color: var(--ink);
      }

      .topbar {
        position: sticky;
        top: 0;
        z-index: 1020;
        background: rgba(255,255,255,.85);
        backdrop-filter: blur(10px);
        border-bottom: 1px solid rgba(17, 24, 39, 0.06);
        box-shadow: 0 10px 35px rgba(17, 24, 39, 0.06);
      }

      .brand {
        font-weight: 900;
        letter-spacing: -0.6px;
      }

      .brand .dot { color: var(--brand); }

      .shell { max-width: 1200px; }

      .card-elev {
        background: var(--card);
        border: 1px solid rgba(17, 24, 39, 0.06);
        border-radius: 16px;
        box-shadow: 0 18px 45px rgba(17, 24, 39, 0.08);
      }

      .shadow-soft { box-shadow: 0 12px 30px rgba(17, 24, 39, 0.07); }

      .pill {
        display: inline-flex;
        align-items: center;
        gap: 8px;
        padding: 6px 12px;
        border-radius: 999px;
        font-weight: 700;
        font-size: .85rem;
        border: 1px solid rgba(17, 24, 39, 0.08);
        background: rgba(255, 255, 255, 0.65);
      }

      .pill-admin {
        border-color: rgba(229, 0, 18, 0.25);
        background: rgba(229, 0, 18, 0.08);
        color: #b1000d;
      }

      .pill-user {
        border-color: rgba(17, 24, 39, 0.15);
        background: rgba(17, 24, 39, 0.04);
        color: #111827;
      }

      .searchbox {
        border-radius: 12px;
        border: 1px solid rgba(17, 24, 39, 0.10);
        padding: 10px 12px;
        background: rgba(255,255,255,.85);
      }

      .search-addon {
        border-radius: 12px 0 0 12px;
        border: 1px solid rgba(17,24,39,.10);
      }

      .searchbox:focus {
        box-shadow: 0 0 0 .25rem var(--ring);
        border-color: rgba(229, 0, 18, 0.35);
      }

      .btn-brand {
        background: var(--brand);
        border-color: var(--brand);
        border-radius: 12px;
        font-weight: 800;
      }

      .btn-brand:hover {
        background: #c80010;
        border-color: #c80010;
      }

      .table thead th {
        font-size: .8rem;
        text-transform: uppercase;
        letter-spacing: 1px;
        color: var(--muted);
        border-bottom: 1px solid rgba(17, 24, 39, 0.08);
        white-space: nowrap;
      }

      .table tbody td { vertical-align: middle; }

      .table tbody tr:hover {
        background: rgba(17, 24, 39, 0.02);
      }

      .avatar {
        width: 42px; height: 42px;
        border-radius: 14px;
        overflow: hidden;
        border: 1px solid rgba(17, 24, 39, 0.08);
        background: linear-gradient(135deg, rgba(229,0,18,.20), rgba(17,24,39,.06));
        display: inline-flex;
        align-items: center;
        justify-content: center;
        font-weight: 900;
        color: rgba(17, 24, 39, 0.8);
      }

      .avatar img { width: 100%; height: 100%; object-fit: cover; }

      .badge-gender {
        border-radius: 999px;
        font-weight: 800;
        font-size: .8rem;
        padding: 6px 10px;
        border: 1px solid rgba(17, 24, 39, 0.10);
        background: rgba(255,255,255,.7);
        color: #111827;
        white-space: nowrap;
      }

      .muted { color: var(--muted); }

      .page-title {
        font-weight: 900;
        letter-spacing: -0.8px;
      }

      .subtle { color: var(--muted); }

      .hint { font-size: .9rem; color: var(--muted); }

      .footer-note { font-size: .85rem; color: var(--muted); }

      .action-btn { border-radius: 10px; }

      .empty {
        padding: 70px 20px;
        text-align: center;
        color: var(--muted);
      }

      .empty i {
        font-size: 56px;
        color: rgba(17, 24, 39, 0.20);
      }

      /* Make Bootstrap components follow the same rounded style */
      :root {
        --bs-border-radius: 0.9rem;
        --bs-border-radius-lg: 1rem;
        --bs-border-radius-sm: 0.7rem;
        --bs-body-color: var(--ink);
        --bs-body-font-family: 'Outfit', system-ui, -apple-system, "Segoe UI", Roboto, Arial, "Noto Sans", "Liberation Sans", sans-serif;
      }

      /* Mobile tighten */
      @media (max-width: 576px) {
        .page-title { font-size: 1.35rem; }
        .topbar .brand { font-size: 1.15rem !important; }
      }
    </style>
  </head>

<body>
  <!-- TOP -->
  <div class="topbar">
    <div class="container shell d-flex justify-content-between align-items-center">
      <div class="brand">Admin.Users</div>

      <a class="btn btn-danger"
         href="${pageContext.request.contextPath}/admin/users/create">
        + Tạo user
      </a>
    </div>
  </div>

  <div class="container shell my-4">

    <!-- SUCCESS -->
    <c:if test="${not empty successMessage}">
      <div class="alert alert-success">
        ${successMessage}
      </div>
    </c:if>

    <!-- CARD -->
    <div class="card-elev p-3">

      <div class="d-flex justify-content-between align-items-center mb-3">
        <h4>Danh sách user</h4>

        <span class="pill">
          ${fn:length(users)} users
        </span>
      </div>

      <table class="table table-hover">
        <thead>
        <tr>
          <th>Avatar</th>
          <th>Họ tên</th>
          <th>Email</th>
          <th>Giới tính</th>
          <th>Ngày sinh</th>
          <th>Role</th>
          <th>Created</th>
          <th>Action</th>
        </tr>
        </thead>

        <tbody>

        <c:if test="${empty users}">
          <tr>
            <td colspan="8" class="text-center text-muted py-5">
              Không có user nào
            </td>
          </tr>
        </c:if>

        <c:forEach var="u" items="${users}">
          <tr>

            <td>
              <div class="avatar">
                <c:out value="${fn:substring(u.fullName,0,1)}"/>
              </div>
            </td>

            <td>
              <b>${u.fullName}</b><br/>
              <small>ID: ${u.id}</small>
            </td>

            <td>${u.email}</td>

            <td>${u.gender}</td>

            <td>
              <c:out value="${u.birthday}" default="—"/>
            </td>

            <td>
              <c:choose>
                <c:when test="${u.role == 'ADMIN'}">
                  <span class="pill-admin">ADMIN</span>
                </c:when>
                <c:otherwise>
                  <span class="pill-user">USER</span>
                </c:otherwise>
              </c:choose>
            </td>

            <td>
              <fmt:formatDate value="${u.createdAt}" pattern="dd/MM/yyyy HH:mm"/>
            </td>

            <td>
              <a class="btn btn-sm btn-primary"
                 href="${pageContext.request.contextPath}/admin/users/${u.id}/edit">
                Edit
              </a>

              <form method="post"
                    action="${pageContext.request.contextPath}/admin/users/${u.id}/delete"
                    style="display:inline"
                    onsubmit="return confirm('Xoá user?')">

                <button class="btn btn-sm btn-danger">
                  Delete
                </button>

              </form>
            </td>

          </tr>
        </c:forEach>

        </tbody>
      </table>

    </div>
  </div>

  </body>
  </html>